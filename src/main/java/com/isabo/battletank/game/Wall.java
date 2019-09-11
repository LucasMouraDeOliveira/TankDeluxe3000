package com.isabo.battletank.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.isabo.battletank.SettingsManager;

public class Wall extends Body {

	public Wall(double x, double y) {
		this.addFixture(Geometry.createRectangle(SettingsManager.OBSTACLE_WIDTH, SettingsManager.OBSTACLE_HEIGHT), 1, 0.2, 0);
		this.translate(x + SettingsManager.OBSTACLE_WIDTH / 2D, y + SettingsManager.OBSTACLE_HEIGHT / 2D);
		this.setMass(MassType.INFINITE);
	}

}
