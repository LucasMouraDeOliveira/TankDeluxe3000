package com.luma.tankdeluxe.game;

import org.dyn4j.dynamics.Body;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Cell {
	
	private final int x;
	
	private final int y;
	
	private String code;
	
	@JsonIgnore
	private Body body;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
