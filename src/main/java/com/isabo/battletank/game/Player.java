package com.isabo.battletank.game;

import java.time.ZonedDateTime;
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
	private boolean dashing;
	private int dashCooldown;
	private List<Bullet> bullets;
	private int nbShield;
	private int maxBullet;
	private int cooldown;
	private Color color;
	private int score;
	private boolean alive;
	private boolean invincible;
	private ZonedDateTime aliveSince;
	
	private int aimX;
	private int aimY;

	private double turretAngle;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public Player(String name, Color color) {
		this.name = name;
		this.moving = new boolean[4];
		this.shooting = false;
		this.dashing = false;
		this.dashCooldown = 0;
		this.bullets = new ArrayList<>();
		this.maxBullet = SettingsManager.MAX_BULLET;
		this.color = color;
		this.nbShield = 0;
		this.alive = true;
		
		this.addFixture(Geometry.createRectangle(SettingsManager.TANK_WIDTH, SettingsManager.TANK_HEIGHT), 1, 0.5, 0);
		this.setMass(MassType.NORMAL);
		this.setLinearDamping(6);
		this.setAngularDamping(8);
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

	public int getAimX() {
		return aimX;
	}

	public void setAimX(int aimX) {
		this.aimX = aimX;
	}

	public int getAimY() {
		return aimY;
	}

	public void setAimY(int aimY) {
		this.aimY = aimY;
	}

	public double getTurretAngle() {
		return turretAngle;
	}

	public void setTurretAngle(double turretAngle) {
		this.turretAngle = turretAngle;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addShield() {
		this.nbShield++;
	}
	
	public void removeOneShield() {
		if(this.nbShield > 0) {
			this.nbShield--;
		}
	}

	public int getNbShield() {
		return this.nbShield;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public ZonedDateTime getAliveSince() {
		return aliveSince;
	}

	public void setAliveSince(ZonedDateTime aliveSince) {
		this.aliveSince = aliveSince;
	}

	public boolean isDashing() {
		return dashing;
	}

	public void setDashing(boolean dashing) {
		this.dashing = dashing;
	}

	public int getDashCooldown() {
		return dashCooldown;
	}

	public void setDashCooldown(int dashCooldown) {
		this.dashCooldown = dashCooldown;
	}
}
