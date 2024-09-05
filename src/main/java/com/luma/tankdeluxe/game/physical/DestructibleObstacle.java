package com.luma.tankdeluxe.game.physical;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Polygon;

import com.luma.tankdeluxe.SettingsManager;

public class DestructibleObstacle extends Body {

    public DestructibleObstacle(double x, double y) {
        this.addFixture(this.getFixture(), 1, 0.2, 0);
        this.translate(x + SettingsManager.OBSTACLE_WIDTH / 2D, y + SettingsManager.OBSTACLE_HEIGHT / 2D);
        this.setMass(MassType.INFINITE);
    }

    public Polygon getFixture() {
        return Geometry.createRectangle(SettingsManager.OBSTACLE_WIDTH, SettingsManager.OBSTACLE_HEIGHT);
    }

    public void destroy() {
        this.removeAllFixtures();
    }

}
