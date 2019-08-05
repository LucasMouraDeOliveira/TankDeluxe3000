package com.isabo.battletank.game.actions;

import com.isabo.battletank.game.GameServer;
import com.isabo.battletank.game.Player;

public class MovePlayerAction extends GameUpdate {

	public MovePlayerAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act() {
		for(Player player : this.gameServer.getPlayers()) {
			if(player.isMoving(Player.NORTH)) {
				player.setY(player.getY() - 10);
			}
			if(player.isMoving(Player.SOUTH)) {
				player.setY(player.getY() + 10);
			}
		}
	}

}
