package com.isabo.battletank.game.actions;

import org.dyn4j.geometry.Vector2;

import com.isabo.battletank.SettingsManager;
import com.isabo.battletank.game.GameServer;
import com.isabo.battletank.game.player.Player;

public class DashAction extends GameUpdate {

	public DashAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		double force =  Math.pow(SettingsManager.DASH_VELOCITY, 2);
				
		for(Player p : this.gameServer.getPlayers()) {
			
			if(!p.isAlive()) {
				continue;
			}
			
			if(p.getDashCooldown() > 0) {
				p.setDashCooldown(p.getDashCooldown() - delta);

			} else if(p.isDashing() && p.getCharge() <= 0) {
				Vector2 aimForce = new Vector2(p.getAimX() - p.getX(), p.getAimY() - p.getY());
				
				double aimAngle = Math.atan2(aimForce.y, aimForce.x);
				
				Vector2 aimDirection = new Vector2(Math.cos(aimAngle), Math.sin(aimAngle));
				
				Vector2 dash = aimDirection.product(force);
				
				p.applyForce(dash);
				p.setDashCooldown(SettingsManager.DASH_COOLDOWN);
			}
		}
	}
}
