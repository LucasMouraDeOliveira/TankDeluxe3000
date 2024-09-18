package com.luma.tankdeluxe.listener;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.listener.CollisionListenerAdapter;

public class TankRegenerationListener extends CollisionListenerAdapter<Body, BodyFixture> {

	@Override
	public boolean collision(NarrowphaseCollisionData<Body, BodyFixture> collision) {
		System.out.println("Regeneration");
		
		return super.collision(collision);
	}
}
