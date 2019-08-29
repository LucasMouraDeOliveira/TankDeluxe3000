package com.isabo.battletank.game.actions;

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
			
			int velocityX = (int) (b.getVelocity() * Math.sin(b.getAngle()));
			int velocityY = (int) (- b.getVelocity() * Math.cos(b.getAngle()));
			
			if(newX > SettingsManager.CANVAS_WIDTH) {
				velocityX = -velocityX;
			} else if(newX < 0) {
				velocityX = -velocityX;
			}
			
			if(newY > SettingsManager.CANVAS_HEIGHT) {
				velocityY = -velocityY;
			} else if(newY < 0) {
				velocityY = -velocityY;
			}
			
			double newAngle = Math.asin(velocityX / b.getVelocity());
			b.setAngle(newAngle);
			
			// Move
			b.setY(newX);
			b.setX(newY);
		}
		
		
		for (Player p : super.gameServer.getPlayers()) {
			p.setCooldown(p.getCooldown() - delta);
			System.out.println(delta);
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
