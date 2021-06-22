package com.isabo.battletank.game.player;

import com.isabo.battletank.game.Color;

public class Miner extends Player {

	public Miner(String name, Color color) {
		super(name, color, PlayerSpecialization.MINER);
	}

	@Override
	public void applyBuff() {
		this.mineCount = 1;
	}

}
