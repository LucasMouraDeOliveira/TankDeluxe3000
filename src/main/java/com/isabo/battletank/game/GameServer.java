package com.isabo.battletank.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.isabo.battletank.SettingsManager;
import com.isabo.battletank.listener.BulletBulletListener;
import com.isabo.battletank.listener.BulletWallListener;
import com.isabo.battletank.listener.TankBulletListener;

@Component
public class GameServer {
	
	@Autowired
	private LevelBuilder levelBuilder;
	
	private static final int FPS = 20;
	
	private Map<WebSocketSession, Player> players;
	
	private Map<Player, JSONObject> playerActions;
	
	private List<Bullet> bullets;
	private List<Color> availableColor;
	
	private World world;
	private List<Wall> walls;
	private Random random;
	
	public GameServer() {
		this.players = new HashMap<>();
		this.playerActions = new HashMap<>();
		this.bullets = new ArrayList<>();
		this.walls = new ArrayList<>();
		this.availableColor = new LinkedList<Color>(Arrays.asList(Color.values()));
		this.random = new Random();
	}
	
	public void addPlayer(WebSocketSession session, String playerName) {
		Color playerColor = this.availableColor.remove(0);
		Player newPlayer = new Player(playerName, playerColor);
		
		newPlayer.translate(random.nextInt((int)SettingsManager.WORLD_WIDTH), random.nextInt((int)SettingsManager.WORLD_HEIGHT));
		this.players.put(session, newPlayer);
		
		if(this.world != null) {
			this.world.addBody(newPlayer);
		}
	}

	public void removePlayer(WebSocketSession session) {
		this.availableColor.add(this.players.get(session).getColor());
		Player p = this.players.remove(session);
		this.world.removeBody(p);
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
		this.world = new World();
		
		this.world.addListener(new TankBulletListener(this));
		this.world.addListener(new BulletBulletListener(this));
		this.world.addListener(new BulletWallListener());
		
		this.world.setGravity(World.ZERO_GRAVITY);
		this.walls = this.levelBuilder.getNewBorderedLevel();
		this.levelBuilder.addRectangle(this.walls, 5, 3, 12, 3);
		this.levelBuilder.addRectangle(this.walls, 12, 4, 12, 8);
		this.levelBuilder.addRectangle(this.walls, 14, 11, 18, 11);
		this.levelBuilder.addRectangle(this.walls, 8, 12, 9, 13);
		this.levelBuilder.addRectangle(this.walls, 22, 12, 25, 12);
		
		this.walls.stream().forEach(wall -> this.world.addBody(wall));
		
		this.players.entrySet().stream().forEach(entry -> this.world.addBody(entry.getValue()));
		
		new GameLoop(this, 1000 / FPS).start();
	}
	
	public void updateWorld(int elapsedTime) {
		this.world.update(elapsedTime);
	}

	public void notifyPlayers() {
		JSONArray jsonPlayers = new JSONArray();
		JSONArray jsonBullets = new JSONArray();
		JSONArray jsonLevel = new JSONArray();
		
		JSONObject jsonPlayer;
		for(Player player : this.players.values()) {
			jsonPlayer = new JSONObject();
			jsonPlayer.put("x", player.getX() * SettingsManager.SIZE_RATIO);
			jsonPlayer.put("y", player.getY() * SettingsManager.SIZE_RATIO);
			jsonPlayer.put("angle", player.getAngle());
			jsonPlayer.put("turretAngle", player.getTurretAngle());
			jsonPlayer.put("color", player.getColor());
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
		
		
		JSONArray jsonWalls = new JSONArray();
		for(Body wall : this.walls) {
			JSONObject jsonWall = new JSONObject();
			jsonWall.put("x", wall.getTransform().getTranslationX() * SettingsManager.SIZE_RATIO);
			jsonWall.put("y", wall.getTransform().getTranslationY() * SettingsManager.SIZE_RATIO);
			jsonWalls.put(jsonWall);
		}

		JSONObject gameData = new JSONObject();
		gameData.put("players", jsonPlayers);
		gameData.put("bullets", jsonBullets);
		gameData.put("level", jsonLevel);
		gameData.put("walls", jsonWalls);
		
		for(WebSocketSession session : this.players.keySet()) {
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
		this.bullets.remove(bullet);
		this.world.removeBody(bullet);
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void killPlayer(Player player) {
		this.availableColor.add(player.getColor());
		this.playerActions.remove(player);
		
		Map.Entry<WebSocketSession, Player> toBeRemove = null;
		for(Map.Entry<WebSocketSession, Player> session : this.players.entrySet()) {
			if(session.getValue().equals(player)) {
				toBeRemove = session;
			}
		}
		
		if(toBeRemove != null) {
			this.players.remove(toBeRemove.getKey());
			this.world.removeBody(player);
		}
	}
}
