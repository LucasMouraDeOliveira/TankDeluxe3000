package com.isabo.battletank.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.isabo.battletank.SettingsManager;

public class Simulation {

	private final World world;		// The dynamics engine
	private final double scale;		// The pixels per meter scale factor
	
	public Simulation() {
		this.scale = 20;
		this.world = new World();
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
	
	public void addPlayer(Player player) {
		Body tank = new Body();
		
		tank.addFixture(Geometry.createRectangle(SettingsManager.TANK_WIDTH, SettingsManager.TANK_HEIGHT));
		tank.setMass(MassType.NORMAL);
		tank.translate(player.getX(), player.getY());
		tank.rotate(player.getAngle());
		
		world.addBody(tank);
	}
}
