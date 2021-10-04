package com.luma.tankdeluxe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.luma.tankdeluxe.dto.GameDTO;
import com.luma.tankdeluxe.dto.PlayerActionDTO;
import com.luma.tankdeluxe.exception.NoSuchGameException;
import com.luma.tankdeluxe.game.Color;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.Level;
import com.luma.tankdeluxe.game.player.Player;
import com.luma.tankdeluxe.game.player.PlayerSpecialization;

@Service
public class GameService {

	private Map<UUID, GameServer> games;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private LeaderboardService leaderboardService;
	
	
	public GameService() {
		this.games = new HashMap<>();
	}
	
	
	public GameServer startNewGame(String gameName, Level level) {
		GameServer newGame = new GameServer(gameName, level, playerService, leaderboardService);
		
		this.games.put(newGame.getId(), newGame);
		
		newGame.start();
		
		return newGame;
	}
	
	public void connectNewPlayer(UUID gameId, String playerName, PlayerSpecialization specialization, WebSocketSession session) {
		GameServer game = this.getGame(gameId);
		
		Color playerColor = game.getAvailableColor().remove(0);
		Player newPlayer = this.playerService.createPlayer(playerName, playerColor, specialization);
	
		game.createPlayer(session, newPlayer);
	}
	
	public void updatePlayerAction(UUID gameId, WebSocketSession session, JSONObject actions) {
		GameServer game = this.getGame(gameId);

		game.updatePlayerAction(game.getPlayer(session), actions);
	}
	
	public void respawnPlayer(UUID gameId, WebSocketSession session) {
		this.getGame(gameId).respawnPlayer(session);
	}
	
	public void disconnectPlayer(UUID gameId, WebSocketSession session) {
		this.getGame(gameId).removePlayer(session);
	}
	
	public GameServer getGame(UUID gameId) {
		GameServer game = this.games.get(gameId);
		
		if(game == null)
			throw new NoSuchGameException(gameId);
		
		return game;
	}
	
	public List<GameDTO> getGamesInfos() {
		return this.games.values().stream().map(GameDTO::new).collect(Collectors.toList());
	}
	
	@MessageMapping("actions")
	public void playerActionReceive(PlayerActionDTO actions) {
		System.out.println(">>>" + actions.getAction());
	}
}
