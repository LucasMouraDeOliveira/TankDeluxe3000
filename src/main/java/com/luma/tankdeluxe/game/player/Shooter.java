package com.luma.tankdeluxe.game.player;

import java.util.UUID;

import com.luma.tankdeluxe.game.Color;

public class Shooter extends Player {

	public Shooter(UUID userId, String name, Color color) {
		super(userId, name, color, PlayerSpecialization.SHOOTER);
	}

	@Override
	public void applyBuff() {
		this.maxBullet += 2;
	}
	
}
