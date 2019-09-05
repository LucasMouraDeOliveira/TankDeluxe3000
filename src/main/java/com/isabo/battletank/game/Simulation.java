package com.isabo.battletank.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.Force;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.isabo.battletank.SettingsManager;

public class Simulation {

	private final World world;		// The dynamics engine
//	private final double scale;		// The pixels per meter scale factor
	

	public Simulation(boolean[][] level) {
//		this.scale = 20;
		this.world = new World();
		
		this.initializeWorld(level);
	}
	
	public void initializeWorld(boolean[][] level) {
		this.world.setGravity(World.ZERO_GRAVITY);
		
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level.length; j++) {
				Body block = new Body();
				block.addFixture(Geometry.createRectangle(SettingsManager.OBSTACLE_WIDTH, SettingsManager.OBSTACLE_HEIGHT));
				block.translate(i * SettingsManager.OBSTACLE_WIDTH, j * SettingsManager.OBSTACLE_HEIGHT);
				block.setMass(MassType.INFINITE);
			}
		}
	}
	
	public Body createNewTank(int x, int y, int theta) {
		Body tank = new Body();
		
		tank.addFixture(Geometry.createRectangle(SettingsManager.TANK_WIDTH, SettingsManager.TANK_HEIGHT));
		tank.setMass(MassType.NORMAL);
		tank.translate(x, y);
		tank.rotate(theta);
		
		return tank;
	}
	
	public Body createNewBullet(int x, int y, int theta) {
		Body bullet = new Body();
		
		bullet.addFixture(Geometry.createCircle(SettingsManager.BULLET_WIDTH));
		bullet.setMass(MassType.NORMAL);
		bullet.translate(x, y);
		bullet.rotate(theta);
		bullet.applyForce(new Force(SettingsManager.BULLET_VELOCITY, 0));

		return bullet;
	}
	
	public void addTank(Body tank) {
		world.addBody(tank);
	}
	
	public void addBullet(Body bullet) {
		world.addBody(bullet);
	}
	
}
