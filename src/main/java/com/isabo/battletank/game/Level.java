package com.isabo.battletank.game;

import java.util.ArrayList;
import java.util.List;

public class Level {
	
	private List<Coordinate> spone;
	
	private List<Wall> obstalces;
	
	
	public Level() {
		this.spone = new ArrayList<>();
	}
	
	public Level(List<Wall> walls) {
		this.spone = new ArrayList<>();
		this.obstalces = walls;
	}

	
	public void addSpone(Coordinate c) {
		this.spone.add(c);
	}
	
	public void removeSpone(Coordinate c) {
		this.spone.remove(c);
	}
	
	public List<Coordinate> getSpone() {
		return spone;
	}

	public List<Wall> getObstalces() {
		return obstalces;
	}

	public void setObstalces(List<Wall> obstalces) {
		this.obstalces = obstalces;
	}

	
}
