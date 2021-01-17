package com.isabo.battletank.game.physical;

import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Polygon;

import com.isabo.battletank.SettingsManager;

public class Square extends Obstacle {

	public Square(double x, double y) {
		super(x, y);
	}

	public Polygon getFixture() {
		return Geometry.createRectangle(SettingsManager.OBSTACLE_WIDTH, SettingsManager.OBSTACLE_HEIGHT);
	}
}
