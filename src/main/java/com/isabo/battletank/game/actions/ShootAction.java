package com.isabo.battletank.game.actions;

import java.util.ArrayList;
import java.util.List;

import com.isabo.battletank.SettingsManager;
import com.isabo.battletank.game.Bullet;
import com.isabo.battletank.game.GameServer;
import com.isabo.battletank.game.Player;

public class ShootAction extends GameUpdate {
	
	public ShootAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		

		// Remove old bullet
		
		// The remove action needs to be performed in two step
		// because altering an ArrayList in same time of iterating on it
		// is a bad idea.
		
		// Find bullet to remove
		List<Bullet> bulletToRemove = new ArrayList<>();
		for (Bullet b : this.gameServer.getBullets()) {
			if(b.getRemainingBounce() < 0) {
				bulletToRemove.add(b);
			}
		}
		// Remove the bullet
		for (Bullet b : bulletToRemove) {
			super.gameServer.removeBullet(b);
		}

		// Create new bullet
		for (Player p : super.gameServer.getPlayers()) {
			p.setCooldown(p.getCooldown() - delta);

			// Only if cooldown is respected
			if(p.isShooting() && p.getMaxBullet() > p.getBullets().size() && p.getCooldown() <= 0) {
				Bullet newBullet = new Bullet(p);
				
				super.gameServer.addBullet(newBullet);
				p.addBullet(newBullet);
				
				p.setCooldown(SettingsManager.SHOOT_COOLDOWN);
			}
		}
	}
}
