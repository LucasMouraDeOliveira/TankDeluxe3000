package com.isabo.battletank.game.player;

import com.isabo.battletank.game.Color;

public class Speedy extends Player {

	public Speedy(String name, Color color) {
		super(name, color);

		this.bulletVelocity *= 1.2;
	}

}
