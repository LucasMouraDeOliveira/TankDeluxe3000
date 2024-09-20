package com.luma.tankdeluxe.listener;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.listener.CollisionListenerAdapter;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.physical.RegenerationArea;
import com.luma.tankdeluxe.game.player.Player;

public class TankRegenerationListener extends CollisionListenerAdapter<Body, BodyFixture> {

	@Override
	public boolean collision(NarrowphaseCollisionData<Body, BodyFixture> collision) {
		
		if((collision.getBody1() instanceof Player && collision.getBody2() instanceof RegenerationArea) ||
				collision.getBody1() instanceof RegenerationArea && collision.getBody2() instanceof Player) {
			
			Player player = (Player) (collision.getBody1() instanceof Player ? collision.getBody1() : collision.getBody2());
			
			if(player.isAlive() && player.getHealth() < SettingsManager.MAX_HEALTH) {
				player.setHealth(player.getHealth() + 1);
			}
		}
		
		return super.collision(collision);
	}
}
