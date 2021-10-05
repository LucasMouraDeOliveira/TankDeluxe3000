package com.luma.tankdeluxe.game.actions;

import java.util.Map;

import org.json.JSONObject;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.player.Miner;
import com.luma.tankdeluxe.game.player.Player;

public class UpdatePlayerAction extends GameUpdate {

	public UpdatePlayerAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		for(Map.Entry<Player, JSONObject> entry : gameServer.getPlayerActions().entrySet()) {
			Player player = entry.getKey();
			JSONObject obj = entry.getValue();
			if(player != null && obj != null) {
				player.setMoving(Player.NORTH, obj.getBoolean("forward"));
				player.setMoving(Player.SOUTH, obj.getBoolean("backward"));
				player.setMoving(Player.WEST, obj.getBoolean("left"));
				player.setMoving(Player.EAST, obj.getBoolean("right"));
				player.setShooting(obj.getBoolean("shoot"));
				player.setDashing(obj.getBoolean("dash"));
				
				if(player instanceof Miner) {
				    Miner miner = (Miner)player;
				    miner.setPlacingMine(obj.getBoolean("place_mine"));
				}
				
				JSONObject aim = obj.getJSONObject("aim");
				player.setAimX(aim.getInt("x") / SettingsManager.SIZE_RATIO);
				player.setAimY(aim.getInt("y") / SettingsManager.SIZE_RATIO);
			}
		}
	}

}
