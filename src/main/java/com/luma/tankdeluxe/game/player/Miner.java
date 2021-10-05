package com.luma.tankdeluxe.game.player;

import com.luma.tankdeluxe.game.Color;

public class Miner extends Player {
    
    private boolean placingMine;

	public Miner(String name, Color color) {
		super(name, color, PlayerSpecialization.MINER);
	}

	@Override
	public void applyBuff() {
		this.mineCount = 1;
	}
	
    public boolean isPlacingMine() {
        return placingMine;
    }

    public void setPlacingMine(boolean placingMine) {
        this.placingMine = placingMine;
    }

}
