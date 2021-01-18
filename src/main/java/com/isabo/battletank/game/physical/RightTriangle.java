package com.isabo.battletank.game.physical;

import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Polygon;

import com.isabo.battletank.SettingsManager;

public class RightTriangle extends Obstacle {

	public RightTriangle(double x, double y) {
		super(x, y);
	}

	@Override
	public Polygon getFixture() {
		return Geometry.createRightTriangle(SettingsManager.OBSTACLE_WIDTH, SettingsManager.OBSTACLE_HEIGHT);
	}

}
