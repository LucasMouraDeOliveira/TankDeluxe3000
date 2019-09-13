package com.isabo.battletank.game;

import java.util.ArrayList;
import java.util.List;

public class Level {
	
	private List<Coordinate> spawn;
	
	private List<Wall> obstalces;
	
	
	public Level() {
		this.spawn = new ArrayList<>();
	}
	
	public Level(List<Wall> walls) {
		this.spawn = new ArrayList<>();
		this.obstalces = walls;
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

	public List<Wall> getObstalces() {
		return obstalces;
	}

	public void setObstalces(List<Wall> obstalces) {
		this.obstalces = obstalces;
	}

	
}
