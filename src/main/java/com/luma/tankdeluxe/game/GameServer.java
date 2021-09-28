package com.luma.tankdeluxe.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.dyn4j.dynamics.World;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.level.Layout;
import com.luma.tankdeluxe.game.player.Player;
import com.luma.tankdeluxe.listener.BulletBulletListener;
import com.luma.tankdeluxe.listener.BulletWallListener;
import com.luma.tankdeluxe.listener.TankBulletListener;
import com.luma.tankdeluxe.service.LeaderboardService;
import com.luma.tankdeluxe.service.PlayerService;

public class GameServer {
	
	private UUID id;
	private String name;
	private World world;
	private Random random;
	private Level level;
	
	private Map<WebSocketSession, Player> players;
	
	private Map<Player, JSONObject> playerActions;
	
	private List<Bullet> bullets;
	private List<Color> availableColor;
	
	private PlayerService playerService;
	
	private GameScore gameScore;
	
	public GameServer(String name, Level level, PlayerService playerService, LeaderboardService leaderboardService) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.level = level;
		this.players = new HashMap<>();
		this.playerActions = new HashMap<>();
		this.bullets = new ArrayList<>();
		this.availableColor = new LinkedList<>(Arrays.asList(Color.values()));
		this.random = new Random();
		this.gameScore = new GameScore(leaderboardService);
		
		this.playerService = playerService;
		
		this.world = new World();
		
		this.world.addListener(new TankBulletListener(this));
		this.world.addListener(new BulletBulletListener(this));
		this.world.addListener(new BulletWallListener());
		
		this.world.setGravity(World.ZERO_GRAVITY);
		
		this.loadLevel();
	}
	
	private void loadLevel() {
		
		List<Cell> cells = this.level.getLayouts().get(1).getCells();	// TODO manage many layout 
		cells.stream().forEach(cell -> { 
			if(cell.getBody() != null) {
				this.world.addBody(cell.getBody());
			}
		});
	}

	public void createPlayer(WebSocketSession session, Player newPlayer) {
		this.players.put(session, newPlayer);
		
		this.addPlayerToWorld(newPlayer);
		
		this.sendMap(session);
	}
	
	public void respawnPlayer(WebSocketSession session) {
		Player player = this.getPlayer(session);
		
		this.playerService.initializeStats(player);
		this.world.removeBody(player);
		this.gameScore.removeScore(player);
		
		this.addPlayerToWorld(player);
	}
	
	public void addPlayerToWorld(Player player) {
		List<Coordinate> spone = this.level.getSpawn();
		Coordinate playerSpone = spone.get(random.nextInt(spone.size()));
		
		playerService.initializePlayerPosition(player, playerSpone);
		playerService.initializeStats(player);
		
		if(this.world != null) {
			this.world.addBody(player);
		}
		this.gameScore.initScore(player);
	}

	public void removePlayer(WebSocketSession session) {
		this.availableColor.add(this.players.get(session).getColor());
		Player p = this.players.remove(session);
		this.world.removeBody(p);
		this.gameScore.removeScore(p);
	}
	
	public Player getPlayer(WebSocketSession session) {
		return players.get(session);
	}
	
	public List<Player> getPlayers() {
		return this.players.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
	}
	
	public Map<Player, JSONObject> getPlayerActions() {
		return this.playerActions;
	}
	
	public void updatePlayerAction(Player player, JSONObject action) {
		playerActions.put(player, action);
	}
	
	public void start() {
		new GameLoop(this, 1000 / SettingsManager.FPS).start();
	}
	
	public void updateWorld(int elapsedTime) {
		// TODO Exception in thread "Thread-9" java.util.ConcurrentModificationException
		this.world.update(elapsedTime);
	}

	public void notifyPlayers() {
		JSONArray jsonPlayers = new JSONArray();
		JSONArray jsonBullets = new JSONArray();
		JSONArray jsonLevel = new JSONArray();
		JSONObject jsonScore = new JSONObject();
		
		JSONObject jsonPlayer;
		for(Player player : this.getPlayers()) {
			jsonPlayer = new JSONObject();
			jsonPlayer.put("x", player.getX() * SettingsManager.SIZE_RATIO);
			jsonPlayer.put("y", player.getY() * SettingsManager.SIZE_RATIO);
			jsonPlayer.put("angle", player.getAngle());
			jsonPlayer.put("turretAngle", player.getTurretAngle());
			jsonPlayer.put("nbShield", player.getNbShield());
			jsonPlayer.put("color", player.getColor());
			jsonPlayer.put("alive", player.isAlive());
			jsonPlayer.put("invincible", player.isInvincible());
			jsonPlayer.put("name", player.getName());
			jsonPlayer.put("shooting", player.isShooting());
			jsonPlayer.put("charge", player.getCharge());
			jsonPlayers.put(jsonPlayer);
		}
		
		JSONObject jsonBullet;
		for (Bullet bullet : bullets) {
			jsonBullet = new JSONObject();
			jsonBullet.put("x", bullet.getX() * SettingsManager.SIZE_RATIO);
			jsonBullet.put("y", bullet.getY() * SettingsManager.SIZE_RATIO);
			jsonBullet.put("angle", bullet.getAngle());
			jsonBullets.put(jsonBullet);
		}
		
		JSONArray jsonPlayerScores = new JSONArray();
		for(Map.Entry<Player, Integer> entry : this.gameScore.getScores().entrySet()) {
			JSONObject jsonPlayerScore = new JSONObject();
			jsonPlayerScore.put("name", entry.getKey().getName());
			jsonPlayerScore.put("score", entry.getValue());
			jsonPlayerScores.put(jsonPlayerScore);
		}
		jsonScore.put("players", jsonPlayerScores);
		
		Player bestPlayer = this.gameScore.getBestPlayer();
		if(bestPlayer != null) {
			jsonScore.put("bestPlayer", bestPlayer.getName());
			jsonScore.put("bestScore", this.gameScore.getAllTimeHighScore());
		}

		JSONObject gameData = new JSONObject();
		gameData.put("players", jsonPlayers);
		gameData.put("bullets", jsonBullets);
		gameData.put("level", jsonLevel);
		gameData.put("scores", jsonScore);
		
		for(WebSocketSession session : new HashMap<>(this.players).keySet()) {
			// identify current player
			Player p = this.players.get(session);
			if(p != null) {
				for (int i = 0; i < jsonPlayers.length(); i++) {
					JSONObject playerJson = jsonPlayers.getJSONObject(i);
					playerJson.put("self", playerJson.getString("name").equals(p.getName()));
				}
			}
			
			sendWsMessage(gameData.toString(), session);
		}
	}

	private void sendMap(WebSocketSession playerSession) {
		
		JSONArray jsonCells = new JSONArray();
		for(Layout l : this.level.getLayouts()) {
			for(Cell cell : l.getCells()) {
				JSONObject jsonCell = new JSONObject();
				jsonCell.put("x", cell.getX());
				jsonCell.put("y", cell.getY());
				jsonCell.put("code", cell.getCode());
				jsonCells.put(jsonCell);
			}
		}
		
		JSONObject gameData = new JSONObject();
		gameData.put("walls", jsonCells);
		gameData.put("width", this.level.getWidth());
		gameData.put("height", this.level.getHeight());
		
		this.sendWsMessage(gameData.toString(), playerSession);
	}

	private void sendWsMessage(String gameData, WebSocketSession session) {
		new Thread(() -> {
			try {
				session.sendMessage(new TextMessage(gameData));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
		this.world.addBody(bullet);
	}
	
	public void removeBullet(Bullet bullet) {
		bullet.getShooter().getBullets().remove(bullet);
		this.bullets.remove(bullet);
		this.world.removeBody(bullet);
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void killPlayer(Player player) {
		this.gameScore.initScore(player);
		player.setAlive(false);
	}
	
	public GameScore getGameScore() {
		return gameScore;
	}
	
	public Level getLevel() {
		return this.level;
	}

	public String getName() {
		return this.name;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public List<Color> getAvailableColor() {
		return this.availableColor;
	}
}
