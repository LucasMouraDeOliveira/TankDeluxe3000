package com.isabo.battletank.game.player;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.isabo.battletank.SettingsManager;
import com.isabo.battletank.game.Bullet;
import com.isabo.battletank.game.Color;

public abstract class Player extends Body {
	
	private String name;
	private PlayerSpecialization specialization;
	private Color color;
	private boolean moving[];
	
	// Shoot
	private boolean shooting;
	private List<Bullet> bullets;
	private int cooldown;
	protected int maxBullet;
	protected int bulletVelocity;
	private int charge;			// In ms
	
	private boolean dashing;
	private int dashCooldown;
	private int nbShield;
	private int score;
	private boolean alive;
	private boolean invincible;
	private ZonedDateTime aliveSince;
	
	private int aimX;
	private int aimY;

	private double turretAngle;
	protected int mineCount;
	
	// TODO externalize
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	
	public Player(String name, Color color, PlayerSpecialization specialization) {
		this.name = name;
		this.color = color;
		this.specialization = specialization;
		this.moving = new boolean[4];

		this.addFixture(Geometry.createRectangle(SettingsManager.TANK_WIDTH, SettingsManager.TANK_HEIGHT), 1, 0.5, 0);
		this.setMass(MassType.NORMAL);
		this.setLinearDamping(6);
		this.setAngularDamping(8);
	}
	
	public abstract void applyBuff();
	
	

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
	
	public void setBullets(List<Bullet> bullets) {
		this.bullets = new ArrayList<>();
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
	
	public void setNbShield(int nbShield) {
		this.nbShield = nbShield;
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

	public int getBulletVelocity() {
		return bulletVelocity;
	}

	public void setBulletVelocity(int bulletVelocity) {
		this.bulletVelocity = bulletVelocity;
	}

	public PlayerSpecialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(PlayerSpecialization specialization) {
		this.specialization = specialization;
	}

	public int getMineCount() {
		return mineCount;
	}

	public void setMineCount(int mineCount) {
		this.mineCount = mineCount;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public void addCharge(int charge) {
		this.charge += charge;
	}
}
