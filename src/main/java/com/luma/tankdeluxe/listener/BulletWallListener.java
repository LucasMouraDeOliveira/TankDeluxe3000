package com.luma.tankdeluxe.listener;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.listener.CollisionListenerAdapter;

import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.physical.Obstacle;

public class BulletWallListener extends CollisionListenerAdapter<Body, BodyFixture> {


	@Override
	public boolean collision(NarrowphaseCollisionData<Body, BodyFixture> collision) {
		if(collision.getBody1() instanceof Bullet &&collision.getBody2() instanceof Obstacle ||
				collision.getBody2() instanceof Bullet && collision.getBody1() instanceof Obstacle) {

			if(collision.getBody1() instanceof Bullet) {
				((Bullet) collision.getBody1()).bounce();
			} else {
				((Bullet) collision.getBody2()).bounce();
			}
		}
		
		return super.collision(collision);
	}

}
