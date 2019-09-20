package com.isabo.battletank.game;

import java.util.ArrayList;
import java.util.List;

public class Level {
	
	private List<Coordinate> spawn;
	
	private List<Cell> cells;
	
	public Level() {
		this.spawn = new ArrayList<>();
	}
	
	public Level(List<Cell> cells) {
		this.spawn = new ArrayList<>();
		this.cells = cells;
	}

	
	public void addSpawn(Coordinate c) {
		this.spawn.add(c);
	}
	
	public void removeSpawn(Coordinate c) {
		this.spawn.remove(c);
	}
	
	public List<Coordinate> getSpawn() {
		return spawn;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	
}
