package com.isabo.battletank.game;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.isabo.battletank.SettingsManager;

public class Player extends Body {
	
	private String name;
	private boolean moving[];
	private boolean shooting;
	private List<Bullet> bullets;
	private int maxBullet;
	private int cooldown;
	private Color color;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public Player(String name, Color color) {
		this.name = name;
		this.moving = new boolean[4];
		this.shooting = false;
		this.bullets = new ArrayList<>();
		this.maxBullet = SettingsManager.MAX_BULLET;
		this.color = color;
		
		this.addFixture(Geometry.createRectangle(SettingsManager.TANK_WIDTH, SettingsManager.TANK_HEIGHT), 1, 0.5, 0);
		this.setMass(MassType.NORMAL);
	}

	public double getX() {
		return this.getWorldCenter().x;
	}

	public double getY() {
		return this.getWorldCenter().y;
	}

	public double getAngle() {
		return this.getTransform().getRotation();
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
