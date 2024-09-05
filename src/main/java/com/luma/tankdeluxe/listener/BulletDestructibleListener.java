package com.luma.tankdeluxe.listener;

import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;

import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.physical.DestructibleObstacle;

public class BulletDestructibleListener extends ContactAdapter {

    @Override
    public boolean begin(ContactPoint point) {
        if (point.getBody1() instanceof Bullet && point.getBody2() instanceof DestructibleObstacle ||
                point.getBody2() instanceof Bullet && point.getBody1() instanceof DestructibleObstacle) {

            if (point.getBody1() instanceof Bullet) {
                ((Bullet) point.getBody1()).setRemainingBounce(-1);
                ((DestructibleObstacle) point.getBody2()).destroy();
            } else {
                ((Bullet) point.getBody2()).setRemainingBounce(-1);
                ((DestructibleObstacle) point.getBody1()).destroy();
            }
        }

        return super.begin(point);
    }

}
