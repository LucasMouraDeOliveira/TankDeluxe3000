package com.luma.tankdeluxe.game.player;

import java.util.UUID;

import com.luma.tankdeluxe.game.Color;

public class Quicker extends Player {

	public Quicker(UUID userId, String name, Color color) {
		super(userId, name, color, PlayerSpecialization.QUICKER);
	}

	@Override
	public void applyBuff() {
		this.bulletVelocity *= 1.5;
	}

}
