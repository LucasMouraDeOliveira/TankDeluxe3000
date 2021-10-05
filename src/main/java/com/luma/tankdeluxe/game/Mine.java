package com.luma.tankdeluxe.game;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.player.Miner;

public class Mine extends Body {
    
    private Miner miner;
    
    public Mine(Miner miner) {
        this.miner = miner;
        this.addFixture(Geometry.createCircle(0.7), 0.0001, 0, 0);
        this.translate(miner.getX() - SettingsManager.TANK_WIDTH * Math.cos(miner.getTurretAngle() + Math.PI * 0.5), 
                miner.getY() - SettingsManager.TANK_HEIGHT * Math.sin(miner.getTurretAngle() + Math.PI * 0.5));
        this.setMass(MassType.INFINITE);
    }

    public Miner getMiner() {
        return miner;
    }
    
    public double getX() {
        return this.getWorldCenter().x;
    }
    public double getY() {
        return this.getWorldCenter().y;
    }
    
}
