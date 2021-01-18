package com.isabo.battletank.game.physical;

import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Polygon;

import com.isabo.battletank.SettingsManager;

public class RightTriangle extends Obstacle {

	public RightTriangle(double x, double y, double theta) {
		super(x, y);
		
		// To put the right angle to the origin of the cell
		this.translate(SettingsManager.OBSTACLE_WIDTH / 4D, SettingsManager.OBSTACLE_WIDTH / 4D);

		// Rotate around the center of the cell
		this.rotate(theta, x + SettingsManager.OBSTACLE_WIDTH / 2D, y + SettingsManager.OBSTACLE_HEIGHT / 2D);
		
	}

	@Override
	public Polygon getFixture() {
		return Geometry.createRightTriangle(SettingsManager.OBSTACLE_WIDTH, SettingsManager.OBSTACLE_HEIGHT);
	}

}