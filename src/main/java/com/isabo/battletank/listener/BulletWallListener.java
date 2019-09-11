package com.isabo.battletank.listener;

import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;

import com.isabo.battletank.game.Bullet;
import com.isabo.battletank.game.Wall;

public class BulletWallListener extends ContactAdapter {


	@Override
	public boolean begin(ContactPoint point) {
		if(point.getBody1() instanceof Bullet && point.getBody2() instanceof Wall ||
			point.getBody2() instanceof Bullet && point.getBody1() instanceof Wall) {

			if(point.getBody1() instanceof Bullet) {
				((Bullet) point.getBody1()).bounce();
			} else {
				((Bullet) point.getBody2()).bounce();
			}
		}
		
		return super.begin(point);
	}

}
