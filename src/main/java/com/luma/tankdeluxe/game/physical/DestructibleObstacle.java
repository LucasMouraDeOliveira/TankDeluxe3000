package com.luma.tankdeluxe.game.physical;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Polygon;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.level.Cell;

import lombok.Getter;

@Getter
public class DestructibleObstacle extends Body {

    private final Cell cell;

    public DestructibleObstacle(Cell cell, double x, double y) {
        this.cell = cell;
        this.initBody(x, y);
    }

    private void initBody(double x, double y) {
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
