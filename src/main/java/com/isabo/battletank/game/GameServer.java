package com.isabo.battletank.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.isabo.battletank.dto.PlayerDTO;

@Component
public class GameServer {
	
	private static final int FPS = 20;
	
	private Map<WebSocketSession, Player> players;
	
	private Map<Player, JSONObject> playerActions;
	
	public void addPlayer(WebSocketSession session, String playerName) {
		Player player = new Player(playerName);
		this.players = new HashMap<>();
		this.playerActions = new HashMap<>();
		this.players.put(session, player);
	}

	public void removePlayer(WebSocketSession session) {
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
		new GameLoop(this, 1000 / FPS).start();
	}

	public void notifyPlayers() {
		JSONArray array = new JSONArray();
		JSONObject obj;
		for(Player player : players.values()) {
			obj = new JSONObject();
			obj.put("x", player.getX());
			obj.put("y", player.getY());
			obj.put("angle", player.getAngle());
			array.put(obj);
		}
		for(WebSocketSession session : players.keySet()) {
			new Thread(() -> {
				try {
					session.sendMessage(new TextMessage(array.toString()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

}
