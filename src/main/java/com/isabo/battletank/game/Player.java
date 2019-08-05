package com.isabo.battletank.game;

public class Player {
	
	private int x;
	
	private int y;
	
	private double angle;
	
	private String name;
	
	private boolean moving[];
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public Player(String name) {
		this.name = name;
		this.x = 0;
		this.y = 0;
		this.angle = 0D;
		this.moving = new boolean[4];
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isMoving(int direction) {
		try {
			return moving[direction];
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public void setMoving(int direction, boolean value) {
		try {
			this.moving[direction] = value;
		} catch(ArrayIndexOutOfBoundsException e) {}
	}

}
