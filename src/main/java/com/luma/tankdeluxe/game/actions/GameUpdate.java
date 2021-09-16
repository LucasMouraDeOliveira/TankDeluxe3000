package com.luma.tankdeluxe.game.actions;

import com.luma.tankdeluxe.game.GameServer;

public abstract class GameUpdate {
	
	protected GameServer gameServer;
	
	public GameUpdate(GameServer gameServer) {
		this.gameServer = gameServer;
	}
	
	public abstract void act(int delta);

}
