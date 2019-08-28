package com.isabo.battletank.game;

import org.springframework.beans.factory.annotation.Autowired;

import com.isabo.battletank.SettingsManager;

public class Bullet {
	
	@Autowired
	private SettingsManager settingsManager;
	
	private Player shooter;
	private double x;
	private double y;
	private int velocity;
	private double angle;
	
	public Bullet(Player shooter) {
		this.shooter = shooter;
		this.x = shooter.getX();
		this.y = shooter.getY();
		this.velocity = settingsManager.getBulletVelocity();
		this.angle = shooter.getAngle();
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
	
}
