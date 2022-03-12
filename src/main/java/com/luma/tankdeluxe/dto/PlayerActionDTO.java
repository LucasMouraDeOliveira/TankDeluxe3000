package com.luma.tankdeluxe.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerActionDTO {

	private UUID userId;
	private UUID gameId;
	
	private Boolean forward;
	private Boolean backward;
	private Boolean left;
	private Boolean right;
	private Boolean shoot;
	private Boolean dash;
	private Boolean charging;
	
	@JsonProperty("place_mine")
	private Boolean placeMine;

	private AimDTO aim;
	
}
