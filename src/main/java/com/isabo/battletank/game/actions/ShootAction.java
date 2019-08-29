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
		// Move existing bullet
		for (Bullet b : super.gameServer.getBullets()) {
			// bounce detection
			int newX = (int) Math.round(b.getX() + b.getVelocity() * Math.sin(b.getAngle()));
			int newY = (int) Math.round(b.getY() - b.getVelocity() * Math.cos(b.getAngle()));
			
			
			if(newX > SettingsManager.CANVAS_WIDTH || newX < 0) {
				b.setAngle(180 - b.getAngle());
				b.bounce();
			}
			
			if(newY > SettingsManager.CANVAS_HEIGHT || newY < 0) {
				b.setAngle(180 - 3 * b.getAngle());
				b.bounce();
			}
			
			// Move
			b.setX(newX);
			b.setY(newY);
		}
		
		
		for (Player p : super.gameServer.getPlayers()) {
			p.setCooldown(p.getCooldown() - delta);

			// Remove old bullet
			
			// The remove action needs to be performed in two step
			// because altering an ArrayList in same time of iterating on it
			// is a bad idea.

			// Find bullet to remove
			List<Bullet> bulletToRemove = new ArrayList<>();
			for (Bullet b : p.getBullets()) {
				if(b.getRemainingBounce() < 0) {
					bulletToRemove.add(b);
				}
			}
			// Remove the bullet
			for (Bullet b : bulletToRemove) {
				p.getBullets().remove(b);
				super.gameServer.getBullets().remove(b);
			}
			
			
			// Create new bullet
			if(p.isShooting() && p.getMaxBullet() > p.getBullets().size() && p.getCooldown() <= 0) {
				Bullet newBullet = new Bullet(p);
				
				super.gameServer.addBullet(newBullet);
				p.addBullet(newBullet);
				
				p.setCooldown(SettingsManager.SHOOT_COOLDOWN);
			}
		}
	}
}
