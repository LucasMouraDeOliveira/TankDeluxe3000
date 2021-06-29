package com.isabo.battletank.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import com.isabo.battletank.SettingsManager;
import com.isabo.battletank.game.player.Player;

public class Bullet extends Body{
	
	private Player shooter;
	private int remainingBounce;
	private Vector2 angle;
	
	public Bullet(Player shooter) {
		this.shooter = shooter;
		this.remainingBounce = SettingsManager.MAX_BOUNCE;
		
		this.angle = new Vector2(shooter.getTurretAngle() + Math.PI * 0.5);

		this.addFixture(Geometry.createCircle(0.7), 0.0001, 0, 1);
		this.translate(shooter.getX() - SettingsManager.TANK_WIDTH * Math.cos(shooter.getTurretAngle() + Math.PI * 0.5), 
						shooter.getY() - SettingsManager.TANK_HEIGHT * Math.sin(shooter.getTurretAngle() + Math.PI * 0.5));
		this.setMass(MassType.NORMAL);
		this.setVelocity(shooter.getBulletVelocity());
	}
	
	public void setVelocity(int velocity) {
		this.setLinearVelocity(angle.product(-velocity));
	}
	
	public Player getShooter() {
		return shooter;
	}
	public void setShooter(Player shooter) {
		this.shooter = shooter;
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
