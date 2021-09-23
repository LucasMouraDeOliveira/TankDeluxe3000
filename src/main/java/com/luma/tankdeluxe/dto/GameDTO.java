package com.luma.tankdeluxe.dto;

import java.util.UUID;

import com.luma.tankdeluxe.game.GameServer;

public class GameDTO {
	
	private UUID gameId;
	private String gameName;
	private int playerNumber;
	
	public GameDTO(GameServer game) {
		this.gameId = game.getId();
		this.gameName = game.getName();
		this.playerNumber = game.getPlayers().size();
	}

	public UUID getGameId() {
		return gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}
}
