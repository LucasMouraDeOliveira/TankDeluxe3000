package com.isabo.battletank.game.actions;

import com.isabo.battletank.game.Bullet;
import com.isabo.battletank.game.GameServer;
import com.isabo.battletank.game.Player;

public class ShootAction extends GameUpdate {

	public ShootAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act() {
		// Move existing bullet
		for (Bullet b : super.gameServer.getBullets()) {
			System.out.println("Shoot ! " + b.getX() + " " + b.getY());
			b.setY((int) Math.round(b.getY() - b.getVelocity() * Math.cos(b.getAngle())));
			b.setX((int) Math.round(b.getX() + b.getVelocity() * Math.sin(b.getAngle())));
		}
		
		
		// Create new bullet
		for (Player p : super.gameServer.getPlayers()) {
			if(p.isShooting()) {
				super.gameServer.addBullet(new Bullet(p));
			}
		}
	}
}
