package com.luma.tankdeluxe.game.player;

import com.luma.tankdeluxe.game.Color;

public class Quicker extends Player {

	public Quicker(String name, Color color) {
		super(name, color, PlayerSpecialization.QUICKER);
	}

	@Override
	public void applyBuff() {
		this.bulletVelocity *= 1.5;
	}

}
