package com.luma.tankdeluxe.game.player;

import com.luma.tankdeluxe.game.Color;

public class Sniper extends Player {

	public Sniper(String name, Color color) {
		super(name, color, PlayerSpecialization.SNIPER);
	}

	@Override
	public void applyBuff() {
		this.maxBullet = 1;
		this.bulletVelocity *= 2.5;
	}

}
