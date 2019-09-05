package com.isabo.battletank.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

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
	private List<Body> walls;
	
	public GameServer() {
		this.players = new HashMap<>();
		this.playerActions = new HashMap<>();
		this.bullets = new ArrayList<>();
		this.walls = new ArrayList<>();
		this.availableColor = new LinkedList<Color>(Arrays.asList(Color.values()));
	}
	
	public void addPlayer(WebSocketSession session, String playerName) {
		Color playerColor = this.availableColor.remove(0);
		this.players.put(session, new Player(playerName, playerColor));
	}

	public void removePlayer(WebSocketSession session) {
		this.availableColor.add(this.players.get(session).getColor());
		this.players.remove(session);
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
		this.world.setGravity(World.ZERO_GRAVITY);
		this.walls = this.levelBuilder.getNewBorderedLevel();
		for(Body wall : this.walls) {
			this.world.addBody(wall);
		}
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
			jsonPlayer.put("x", player.getX());
			jsonPlayer.put("y", player.getY());
			jsonPlayer.put("angle", player.getAngle());
			jsonPlayer.put("color", player.getColor());
			jsonPlayers.put(jsonPlayer);
		}
		
		JSONObject jsonBullet;
		for (Bullet bullet : bullets) {
			jsonBullet = new JSONObject();
			jsonBullet.put("x", bullet.getX());
			jsonBullet.put("y", bullet.getY());
			jsonBullet.put("angle", bullet.getAngle());
			jsonBullets.put(jsonBullet);
		}
		
		
		JSONArray jsonWalls = new JSONArray();
		for(Body wall : this.walls) {
			JSONObject jsonWall = new JSONObject();
			jsonWall.put("x", wall.getTransform().getTranslationX());
			jsonWall.put("y", wall.getTransform().getTranslationY());
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
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void killPlayer(Player player) {
		this.availableColor.add(player.getColor());
		
		this.playerActions.remove(player);
		for(Map.Entry<WebSocketSession, Player> session : this.players.entrySet()) {
			if(session.getValue().equals(player)) {
				players.remove(session.getKey());
			}
		}
	}
}
