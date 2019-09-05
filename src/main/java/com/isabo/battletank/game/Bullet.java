package com.isabo.battletank.game;

import org.dyn4j.dynamics.Body;

import com.isabo.battletank.SettingsManager;

public class Bullet extends Body {
	
	private Player shooter;
	private double x;
	private double y;
	private int velocity;
	private double angle;
	private int remainingBounce;
	
	public Bullet(Player shooter) {
		this.shooter = shooter;
		this.x = shooter.getX();
		this.y = shooter.getY();
		this.velocity = SettingsManager.BULLET_VELOCITY;
		this.angle = shooter.getAngle();
		this.remainingBounce = SettingsManager.MAX_BOUNCE;
	}
	
	public Player getShooter() {
		return shooter;
	}
	public void setShooter(Player shooter) {
		this.shooter = shooter;
	}
	public int getVelocity() {
		return velocity;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public int getRemainingBounce() {
		return remainingBounce;
	}

	public void setRemainingBounce(int remainingBounce) {
		this.remainingBounce = remainingBounce;
	}

	public void bounce() {
		this.remainingBounce--;
	}
	
}
