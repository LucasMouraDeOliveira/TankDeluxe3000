package com.luma.tankdeluxe.dto;

import java.util.UUID;

import com.luma.tankdeluxe.game.GameServer;

import lombok.Getter;

@Getter
public class GameDTO {
	
	private UUID id;
	private String name;
	private int playerCount;
	
	public GameDTO(GameServer game) {
		this.id = game.getId();
		this.name = game.getName();
		this.playerCount = game.getPlayers().size();
	}

}
