package com.isabo.battletank.game.physical;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Polygon;

public abstract class Obstacle extends Body {

	public Obstacle(double x, double y) {
		this.addFixture(this.getFixture(), 1, 0.2, 0);
		this.translate(x, y);
		this.setMass(MassType.INFINITE);
	}

	public abstract Polygon getFixture();
	
}
