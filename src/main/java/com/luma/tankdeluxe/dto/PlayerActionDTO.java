package com.luma.tankdeluxe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerActionDTO {

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
