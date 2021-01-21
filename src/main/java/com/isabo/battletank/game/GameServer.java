package com.isabo.battletank.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dyn4j.dynamics.World;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.isabo.battletank.SettingsManager;
import com.isabo.battletank.game.level.Layout;
import com.isabo.battletank.listener.BulletBulletListener;
import com.isabo.battletank.listener.BulletWallListener;
import com.isabo.battletank.listener.TankBulletListener;

@Component
public class GameServer {
	
	@Autowired
	private LevelBuilder levelBuilder;
	
//	private TextFileLevelBuilder levelBuilder;
	
	private Map<WebSocketSession, Player> players;
	
	private Map<Player, JSONObject> playerActions;
	
	private List<Bullet> bullets;
	private List<Color> availableColor;
	
	private World world;
	private Random random;
	private Level level;
	
	private GameScore gameScore;
	
	public GameServer() {
		this.players = new HashMap<>();
		this.playerActions = new HashMap<>();
		this.bullets = new ArrayList<>();
		this.availableColor = new LinkedList<>(Arrays.asList(Color.values()));
		this.random = new Random();
		this.gameScore = new GameScore();
//		this.levelBuilder = new TextFileLevelBuilder();
		
		this.world = new World();
		
		this.world.addListener(new TankBulletListener(this));
		this.world.addListener(new BulletBulletListener(this));
		this.world.addListener(new BulletWallListener());
		
		this.world.setGravity(World.ZERO_GRAVITY);
		
//	    loadLevel("forest");
	    
	    
//		List<Cell> cells = this.level.getCells(); 
//		cells.stream().forEach(cell -> { if(cell.hasWall()) {
//			this.world.addBody(cell.getWall());
//		}});
	}

//	private void loadLevel(String levelName) {
//		try {
//	    	ClassPathResource resource = new ClassPathResource("static/map/" + levelName);
//			File file = resource.getFile();
//			this.level = this.levelBuilder.loadLevel(file.toPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void loadLevel(String levelName) throws IOException {
		this.level = this.levelBuilder.loadLevel(levelName);
		
		List<Cell> cells = this.level.getLayouts().get(1).getCells();	// TODO manage many layout 
		cells.stream().forEach(cell -> { 
			if(cell.getBody() != null) {
				this.world.addBody(cell.getBody());
			}
		});
	}

	public void addPlayer(WebSocketSession session, String playerName) {
		Color playerColor = this.availableColor.remove(0);
		Player newPlayer = new Player(playerName, playerColor);
		
		List<Coordinate> spone = this.level.getSpawn();
		Coordinate playerSpone = spone.get(random.nextInt(spone.size()));
		
		newPlayer.translate(playerSpone.getX(), playerSpone.getY());
		this.players.put(session, newPlayer);
		
		if(this.world != null) {
			this.world.addBody(newPlayer);
		}
		
		this.gameScore.initScore(newPlayer);
	}
	
	public void respawnPlayer(WebSocketSession session) {
		Player player = this.players.get(session);
		
		this.world.removeBody(player);
		this.gameScore.removeScore(player);
		
		List<Coordinate> spone = this.level.getSpawn();
		Coordinate playerSpone = spone.get(random.nextInt(spone.size()));
		
		player.translate(playerSpone.getX(), playerSpone.getY());
		player.setActive(true);
		player.setAlive(true);
		
		this.world.addBody(player);
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
		return new ArrayList<>(this.players.values());
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
		this.world.update(elapsedTime);
	}

	public void notifyPlayers() {
		JSONArray jsonPlayers = new JSONArray();
		JSONArray jsonBullets = new JSONArray();
		JSONArray jsonLevel = new JSONArray();
		JSONObject jsonScore = new JSONObject();
		
		JSONObject jsonPlayer;
		for(Player player : this.players.values()) {
			jsonPlayer = new JSONObject();
			jsonPlayer.put("x", player.getX() * SettingsManager.SIZE_RATIO);
			jsonPlayer.put("y", player.getY() * SettingsManager.SIZE_RATIO);
			jsonPlayer.put("angle", player.getAngle());
			jsonPlayer.put("turretAngle", player.getTurretAngle());
			jsonPlayer.put("nbShield", player.getNbShield());
			jsonPlayer.put("color", player.getColor());
			jsonPlayer.put("alive", player.isAlive());
			jsonPlayer.put("name", player.getName());
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
		
		
		JSONArray jsonCells = new JSONArray();
		for(Layout l : this.level.getLayouts()) {
			for(Cell cell : l.getCells()) {
				JSONObject jsonCell = new JSONObject();
				jsonCell.put("x", cell.getX());
				jsonCell.put("y", cell.getY());
	//			jsonCell.put("floorId", cell.getFloorId());
	//			jsonCell.put("obstacle", true);
				jsonCell.put("code", cell.getCode());
				jsonCells.put(jsonCell);
			}
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
		gameData.put("walls", jsonCells);
		gameData.put("scores", jsonScore);
		
		for(WebSocketSession session : new HashMap<>(this.players).keySet()) {
			new Thread(() -> {
				try {
					session.sendMessage(new TextMessage(gameData.toString()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
		}
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
}
