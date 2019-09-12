package com.isabo.battletank.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import com.isabo.battletank.SettingsManager;

public class Bullet extends Body{
	
	private Player shooter;
	private int remainingBounce;
	
	public Bullet(Player shooter) {
		this.shooter = shooter;
		this.remainingBounce = SettingsManager.MAX_BOUNCE;
		
		Vector2 r = new Vector2(shooter.getTurretAngle() + Math.PI * 0.5);
//		Vector2 t = new Vector2(shooter.getX(), shooter.getY() + SettingsManager.TANK_HEIGHT / 2);
		
//		Vector2 p = shooter.getWorldCenter().sum(r.product(-0.9));

		this.addFixture(Geometry.createCircle(0.7), 0.0001, 0, 1);
		this.translate(shooter.getX() - SettingsManager.TANK_WIDTH * Math.cos(shooter.getTurretAngle() + Math.PI * 0.5), 
						shooter.getY() - SettingsManager.TANK_HEIGHT * Math.sin(shooter.getTurretAngle() + Math.PI * 0.5));
		this.setLinearVelocity(r.product(-SettingsManager.BULLET_VELOCITY));
		this.setMass(MassType.NORMAL);
		
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
