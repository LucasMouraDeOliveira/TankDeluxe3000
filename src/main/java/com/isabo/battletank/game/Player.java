package com.isabo.battletank.game;

import java.util.ArrayList;
import java.util.List;

import com.isabo.battletank.SettingsManager;

public class Player {
	
	private int x;
	private int y;
	private double angle;
	private String name;
	private boolean moving[];
	private boolean shooting;
	private List<Bullet> bullets;
	private int maxBullet;
	private int cooldown;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public Player(String name) {
		this.name = name;
		this.x = 400;
		this.y = 400;
		this.angle = 0D;
		this.moving = new boolean[4];
		this.shooting = false;
		this.bullets = new ArrayList<>();
		this.maxBullet = SettingsManager.MAX_BULLET;
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

	public void setShooting(boolean isShooting) {
		this.shooting = isShooting;
	}

	public boolean isShooting() {
		return shooting;
	}
	
	public void addBullet(Bullet b) {
		this.bullets.add(b);
	}
	
	public List<Bullet> getBullets() {
		return this.bullets;
	}

	public int getMaxBullet() {
		return maxBullet;
	}

	public void setMaxBullet(int maxBullet) {
		this.maxBullet = maxBullet;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

}
