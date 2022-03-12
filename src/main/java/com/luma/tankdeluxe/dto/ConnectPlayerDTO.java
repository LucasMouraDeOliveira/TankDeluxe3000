package com.luma.tankdeluxe.dto;

import java.util.UUID;

import com.luma.tankdeluxe.game.player.PlayerSpecialization;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConnectPlayerDTO {

	private UUID userId;
	
	private PlayerSpecialization specialization;
	
}
