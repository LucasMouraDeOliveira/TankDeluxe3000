package com.isabo.battletank.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.isabo.battletank.SettingsManager;

@Component
public class GameServer {
	
	@Autowired
	private LevelBuilder levelBuilder;
	
	private Simulation simulation;
	
	private Map<WebSocketSession, Player> players;
	private Map<Player, JSONObject> playerActions;
	
	private List<Bullet> bullets;
	private List<Color> availableColor;
	
	private boolean [][] level;
	
	public GameServer() {
		this.players = new HashMap<>();
		this.playerActions = new HashMap<>();
		this.bullets = new ArrayList<>();
		this.availableColor = new LinkedList<Color>(Arrays.asList(Color.values()));
	}
	
	public void addPlayer(WebSocketSession session, String playerName) {
		Color playerColor = this.availableColor.remove(0);
		Body tank = this.simulation.createNewTank(400, 400, 0);
		Player newPlayer = new Player(playerName, playerColor, tank);
		
		this.players.put(session, newPlayer);
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
		System.out.println("Start game");
		
		this.level = this.levelBuilder.getNewBorderedLevel();

		this.level[7][7] = true;
		this.level[8][8] = true;
		this.level[8][7] = true;
		this.level[7][8] = true;
		
		this.level[14][7] = true;
		this.level[14][8] = true;
		this.level[15][7] = true;
		this.level[15][8] = true;
		
		this.simulation = new Simulation(level);
		
		new GameLoop(this, 1000 / SettingsManager.FPS).start();
	}

	public void notifyPlayers() {
		JSONArray jsonPlayers = new JSONArray();
		JSONArray jsonBullets = new JSONArray();
		JSONArray jsonLevel = new JSONArray();
		
		JSONObject jsonPlayer;
		for(Player player : this.players.values()) {
			jsonPlayer = new JSONObject();
			Vector2 pos = player.getTank().getLocalCenter();
			jsonPlayer.put("x", pos.getXComponent());
			jsonPlayer.put("y", pos.getYComponent());
			jsonPlayer.put("angle", player.getTank().getTorque());
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
		
		for (int i = 0; i < level.length; i++) {
			JSONArray jsonObstacleRow = new JSONArray();
			for (int j = 0; j < level[0].length; j++) {
				jsonObstacleRow.put(this.level[i][j]);
			}
			jsonLevel.put(jsonObstacleRow);
		}
		
		JSONObject gameData = new JSONObject();
		gameData.put("players", jsonPlayers);
		gameData.put("bullets", jsonBullets);
		gameData.put("level", jsonLevel);
		
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

	public boolean[][] getLevel() {
		return level;
	}

	public void setLevel(boolean[][] level) {
		this.level = level;
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}
