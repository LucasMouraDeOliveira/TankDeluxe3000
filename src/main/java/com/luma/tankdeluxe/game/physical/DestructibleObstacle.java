package com.luma.tankdeluxe.game.physical;

import java.util.Map;

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

    private int currentHealth;
    private Map<Integer, String> healthStatus;

    public DestructibleObstacle(Cell cell, double x, double y, Map<Integer, String> healthStatus) {
        this.cell = cell;
        this.healthStatus = healthStatus;
        this.currentHealth = healthStatus.keySet().stream().mapToInt(i -> i).max().getAsInt();
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

    public void hit() {
        this.currentHealth = Math.max(0, this.currentHealth - 1);
        if (this.currentHealth == 0) {
            this.removeAllFixtures();
        }
    }

    public String getCurrentStatus() {
        return this.healthStatus.get(this.currentHealth);
    }
}
