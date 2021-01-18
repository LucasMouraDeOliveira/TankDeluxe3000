package com.isabo.battletank.game.physical;

import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Polygon;

import com.isabo.battletank.SettingsManager;

public class Square extends Obstacle {

	public Square(double x, double y) {
		super(x, y);
		
		this.translate(SettingsManager.OBSTACLE_WIDTH / 2D, SettingsManager.OBSTACLE_HEIGHT / 2D);
	}

	public Polygon getFixture() {
		return Geometry.createRectangle(SettingsManager.OBSTACLE_WIDTH, SettingsManager.OBSTACLE_HEIGHT);
	}
}
