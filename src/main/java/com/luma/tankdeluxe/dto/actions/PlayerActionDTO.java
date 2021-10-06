package com.luma.tankdeluxe.dto.actions;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PlayerActionDTO {

	private UUID gameId;
	private String gameAccessToken;
	private ControlsDTO controls;
	
}
