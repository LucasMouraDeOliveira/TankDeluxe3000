package com.luma.tankdeluxe.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GameStateDTO {

	private List<BodyDTO> players;
	private List<BodyDTO> bullets;
	private List<BodyDTO> mines;
	
}
