package com.isabo.battletank.game.player;

import com.isabo.battletank.game.Color;

public class Minor extends Player {

	public Minor(String name, Color color) {
		super(name, color);
		
		this.mineCount = 1;
	}

}
