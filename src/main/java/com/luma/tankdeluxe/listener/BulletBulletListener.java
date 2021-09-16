package com.luma.tankdeluxe.listener;

import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;

import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.GameServer;

public class BulletBulletListener extends ContactAdapter {
	
	private GameServer gameServer;

	public BulletBulletListener(GameServer gs) {
		this.gameServer = gs;
	}
	
	@Override
	public boolean begin(ContactPoint point) {
		if(point.getBody1() instanceof Bullet && point.getBody2() instanceof Bullet) {
			this.gameServer.removeBullet((Bullet) point.getBody1());
			this.gameServer.removeBullet((Bullet) point.getBody2());
		}
		
		return super.begin(point);
	}

}
