package com.luma.tankdeluxe.dto;

import java.util.UUID;

import com.luma.tankdeluxe.game.player.PlayerSpecialization;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JoinGameDTO {

	private UUID gameId;
	private String playerName;
	private String gameAccessToken;
	private PlayerSpecialization spec;
	
}
