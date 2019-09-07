package com.isabo.battletank.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.isabo.battletank.SettingsManager;

public class Bullet extends Body{
	
	private Player shooter;
	private int velocity;
	private int remainingBounce;
	
	public Bullet(Player shooter) {
		this.shooter = shooter;
		this.velocity = SettingsManager.BULLET_VELOCITY;
		this.remainingBounce = SettingsManager.MAX_BOUNCE;
		
		this.addFixture(Geometry.createCircle(3), 0.0001, 0, 1);
		this.translate(shooter.getX() + 40, shooter.getY());
		this.setLinearVelocity(SettingsManager.BULLET_VELOCITY, 0);
		this.setMass(MassType.NORMAL);
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
		return this.getWorldCenter().x;
	}
	public double getY() {
		return this.getWorldCenter().y;
	}
	public double getAngle() {
		return this.getTransform().getRotation();
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
