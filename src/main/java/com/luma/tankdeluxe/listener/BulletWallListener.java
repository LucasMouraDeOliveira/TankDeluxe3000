package com.luma.tankdeluxe.listener;

import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;

import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.physical.Obstacle;

public class BulletWallListener extends ContactAdapter {


	@Override
	public boolean begin(ContactPoint point) {
		if(point.getBody1() instanceof Bullet && point.getBody2() instanceof Obstacle ||
			point.getBody2() instanceof Bullet && point.getBody1() instanceof Obstacle) {

			if(point.getBody1() instanceof Bullet) {
				((Bullet) point.getBody1()).bounce();
			} else {
				((Bullet) point.getBody2()).bounce();
			}
		}
		
		return super.begin(point);
	}

}
