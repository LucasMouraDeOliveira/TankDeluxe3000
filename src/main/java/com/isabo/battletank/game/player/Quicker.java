package com.isabo.battletank.game.player;

import com.isabo.battletank.game.Color;

public class Quicker extends Player {

	public Quicker(String name, Color color) {
		super(name, color, PlayerSpecialization.QUICKER);
	}

	@Override
	public void applyBuff() {
		this.bulletVelocity *= 1.5;
	}

}
