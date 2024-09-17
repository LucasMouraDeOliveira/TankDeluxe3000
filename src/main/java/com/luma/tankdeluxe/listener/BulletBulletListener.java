package com.luma.tankdeluxe.listener;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.listener.CollisionListenerAdapter;

import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.GameServer;

public class BulletBulletListener extends CollisionListenerAdapter<Body, BodyFixture> {
	
	private GameServer gameServer;

	public BulletBulletListener(GameServer gs) {
		this.gameServer = gs;
	}
	
	@Override
	public boolean collision(NarrowphaseCollisionData<Body, BodyFixture> collision) {
		if(collision.getBody1() instanceof Bullet && collision.getBody2() instanceof Bullet) {
			this.gameServer.removeBullet((Bullet) collision.getBody1());
			this.gameServer.removeBullet((Bullet) collision.getBody2());
		}
		
		return super.collision(collision);
	}

}
