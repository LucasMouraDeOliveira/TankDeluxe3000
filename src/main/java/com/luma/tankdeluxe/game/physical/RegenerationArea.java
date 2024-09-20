package com.luma.tankdeluxe.game.physical;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.level.Cell;

import lombok.Getter;

@Getter
public class RegenerationArea extends Body {

	private final Cell cell;
	
	public RegenerationArea(Cell cell, double x, double y) {
        this.cell = cell;
        
        BodyFixture fixture = new BodyFixture(Geometry.createCircle(SettingsManager.REGENERATION_RADIUS));
        fixture.setSensor(true);
        
        this.addFixture(fixture);
        this.translate(x, y);
    }
}
