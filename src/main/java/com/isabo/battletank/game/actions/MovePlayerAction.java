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
				player.setY((int) Math.round(player.getY() - 5 * Math.cos(player.getAngle())));
				player.setX((int) Math.round(player.getX() + 5 * Math.sin(player.getAngle())));
			}
			if(player.isMoving(Player.SOUTH)) {
				player.setY((int) Math.round(player.getY() + 5 * Math.cos(player.getAngle())));
				player.setX((int) Math.round(player.getX() - 5 * Math.sin(player.getAngle())));
			}
			if(player.isMoving(Player.EAST)) {
				player.setAngle(player.getAngle() + 0.1);
			}
			if(player.isMoving(Player.WEST)) {
				player.setAngle(player.getAngle() - 0.1);
			}
		}
	}

}
