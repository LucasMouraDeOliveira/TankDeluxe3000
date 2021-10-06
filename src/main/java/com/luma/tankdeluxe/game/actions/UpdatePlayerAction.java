package com.luma.tankdeluxe.game.actions;

import java.util.Map;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.dto.AimDTO;
import com.luma.tankdeluxe.dto.PlayerActionDTO;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.player.Miner;
import com.luma.tankdeluxe.game.player.Player;

public class UpdatePlayerAction extends GameUpdate {

	public UpdatePlayerAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		for(Map.Entry<Player, PlayerActionDTO> entry : gameServer.getPlayerActions().entrySet()) {
			Player player = entry.getKey();
			PlayerActionDTO actions = entry.getValue();
			
			if(player != null && actions != null) {
				player.setMoving(Player.NORTH, actions.getForward());
				player.setMoving(Player.SOUTH, actions.getBackward());
				player.setMoving(Player.WEST, actions.getLeft());
				player.setMoving(Player.EAST, actions.getRight());
				player.setShooting(actions.getShoot());
				player.setDashing(actions.getDash());
				
				if(player instanceof Miner) {
				    Miner miner = (Miner)player;
				    miner.setPlacingMine(actions.getPlaceMine());
				}
				
				AimDTO aim = actions.getAim();
				player.setAimX(aim.getX() / SettingsManager.SIZE_RATIO);
				player.setAimY(aim.getY() / SettingsManager.SIZE_RATIO);
			}
		}
	}

}
