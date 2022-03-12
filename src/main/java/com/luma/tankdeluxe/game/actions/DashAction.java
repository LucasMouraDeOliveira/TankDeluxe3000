package com.luma.tankdeluxe.game.actions;

import org.dyn4j.geometry.Vector2;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.player.Player;

public class DashAction extends GameUpdate {

	public DashAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		double force =  Math.pow(SettingsManager.DASH_VELOCITY, 2);	// Use delta to weight force
				
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
