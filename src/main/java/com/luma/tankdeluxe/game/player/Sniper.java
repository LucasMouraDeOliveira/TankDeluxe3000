package com.luma.tankdeluxe.game.player;

import java.util.UUID;

import com.luma.tankdeluxe.game.Color;

public class Sniper extends Player {

	public Sniper(UUID userId, String name, Color color) {
		super(userId, name, color, PlayerSpecialization.SNIPER);
	}

	@Override
	public void applyBuff() {
		this.maxBullet = 1;
		this.bulletVelocity *= 2.5;
	}

}
