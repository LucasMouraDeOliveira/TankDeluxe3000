package com.isabo.battletank.game.player;

import com.isabo.battletank.game.Color;

public class Shooter extends Player {

	public Shooter(String name, Color color) {
		super(name, color, PlayerSpecialization.SHOOTER);
	}

	@Override
	public void applyBuff() {
		this.maxBullet += 2;
	}
	
}
