package com.isabo.battletank.game.actions;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import com.isabo.battletank.SettingsManager;
import com.isabo.battletank.game.GameServer;
import com.isabo.battletank.game.player.Player;

public class UpdatePlayerStateAction extends GameUpdate {

	public UpdatePlayerStateAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		ZonedDateTime now = ZonedDateTime.now();
		
		for (Player player : this.gameServer.getPlayers()) {
			if(player.isInvincible() && ChronoUnit.SECONDS.between(player.getAliveSince(), now) > SettingsManager.SPAWN_INVICIBLE_DURATION) {
				player.setInvincible(false);
			}
		}
	}
}
