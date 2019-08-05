package com.isabo.battletank.game.actions;

import com.isabo.battletank.game.GameServer;

public abstract class GameUpdate {
	
	protected GameServer gameServer;
	
	public GameUpdate(GameServer gameServer) {
		this.gameServer = gameServer;
	}
	
	public abstract void act();

}
