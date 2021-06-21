package com.isabo.battletank.game.player;

import com.isabo.battletank.game.Color;

public class Sniper extends Player {

	public Sniper(String name, Color color) {
		super(name, color);
		
		this.bulletPathEnable = true;
	}

}
