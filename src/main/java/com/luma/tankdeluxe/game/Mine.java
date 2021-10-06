package com.luma.tankdeluxe.game;

import com.luma.tankdeluxe.game.player.Miner;

public class Mine {
    
    private Miner miner;
    
    private double x;
    
    private double y;
    
    private boolean blown;
    
    public Mine(Miner miner) {
        this.miner = miner;
        this.x = miner.getX();
        this.y = miner.getY();
    }
    
    public Miner getMiner() {
        return miner;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public boolean isBlown() {
        return blown;
    }
    
    public void setBlown(boolean blown) {
        this.blown = blown;
    }
    
}
