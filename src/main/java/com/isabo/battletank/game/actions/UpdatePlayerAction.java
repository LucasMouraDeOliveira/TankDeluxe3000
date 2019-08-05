package com.isabo.battletank.game.actions;

import java.util.Map;

import org.json.JSONObject;

import com.isabo.battletank.game.GameServer;
import com.isabo.battletank.game.Player;

public class UpdatePlayerAction extends GameUpdate {

	public UpdatePlayerAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act() {
		for(Map.Entry<Player, JSONObject> entry : gameServer.getPlayerActions().entrySet()) {
			Player player = entry.getKey();
			JSONObject obj = entry.getValue();
			player.setMoving(Player.NORTH, obj.getBoolean("forward"));
			player.setMoving(Player.SOUTH, obj.getBoolean("backward"));
			player.setMoving(Player.WEST, obj.getBoolean("left"));
			player.setMoving(Player.EAST, obj.getBoolean("right"));
		}
	}

}
