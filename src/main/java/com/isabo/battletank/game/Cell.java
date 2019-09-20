package com.isabo.battletank.game;

public class Cell {
	
	private final int x;
	
	private final int y;
	
	private int floorId;
	
	private Wall wall;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getFloorId() {
		return floorId;
	}
	
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}
	
	public Wall getWall() {
		return wall;
	}
	
	public void setWall(Wall wall) {
		this.wall = wall;
	}

	public boolean hasWall() {
		return wall != null;
	}

}
