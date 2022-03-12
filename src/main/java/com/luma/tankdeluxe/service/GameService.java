package com.luma.tankdeluxe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.luma.tankdeluxe.dto.GameDTO;
import com.luma.tankdeluxe.dto.PlayerActionDTO;
import com.luma.tankdeluxe.entity.User;
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
	
	public void connectNewPlayer(UUID gameId, User user, PlayerSpecialization specialization) {
		GameServer game = this.getGame(gameId);
		
		Color playerColor = game.getAvailableColor().remove(0);
		
		Player newPlayer = this.playerService.createPlayer(user.getUuid(), user.getLogin(), playerColor, specialization);
	
		game.createPlayer(newPlayer);
	}
	
	public void updatePlayerAction(PlayerActionDTO actions, WebSocketSession session) {
		GameServer game = this.getGame(actions.getGameId());
		Player player = game.getPlayer(actions.getUserId());

		if(player.getSession() == null) {
			player.setSession(session);
		}
		
		if(actions.getForward() != null) {
			game.updatePlayerAction(player, actions);
		} else if(actions.getAim() != null) {
			game.updatePlayerAim(player, actions.getAim());
		}
	}
	
	public void respawnPlayer(UUID gameId, UUID userId) {
		this.getGame(gameId).respawnPlayer(userId);
	}
	
	public void disconnectPlayer(UUID gameId, UUID userId) {
		this.getGame(gameId).removePlayer(userId);
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
	
}
