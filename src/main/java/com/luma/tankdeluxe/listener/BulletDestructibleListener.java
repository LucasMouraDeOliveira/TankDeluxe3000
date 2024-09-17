package com.luma.tankdeluxe.listener;

import java.util.Optional;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.listener.CollisionListenerAdapter;

import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.MapEvent;
import com.luma.tankdeluxe.game.level.Cell;
import com.luma.tankdeluxe.game.physical.DestructibleObstacle;

public class BulletDestructibleListener extends CollisionListenerAdapter<Body, BodyFixture> {

    private GameServer gameServer;

    public BulletDestructibleListener(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
	public boolean collision(NarrowphaseCollisionData<Body, BodyFixture> collision) {

        Optional<Contact> entities = this.getContact(collision);
        if (entities.isPresent()) {
            entities.get().bullet().bounce();

            DestructibleObstacle obstacle = entities.get().obstacle();
            obstacle.hit();

            Cell cell = obstacle.getCell();
            cell.setCode(obstacle.getCurrentStatus());
            this.addMapEvent(cell);
        }

        return super.collision(collision);

    }

    private void addMapEvent(Cell cell) {
        this.gameServer.getMapEvents().add(new MapEvent(cell.getX(), cell.getY(), cell.getCode()));
    }

    private Optional<Contact> getContact(NarrowphaseCollisionData<Body, BodyFixture> collision) {
        return collision.getBody1() instanceof Bullet bullet && collision.getBody2() instanceof DestructibleObstacle obstacle
                ? Optional.of(new Contact(bullet, obstacle))
                : collision.getBody2() instanceof Bullet bullet && collision.getBody1() instanceof DestructibleObstacle obstacle
                        ? Optional.of(new Contact(bullet, obstacle))
                        : Optional.empty();
    }

}

record Contact(Bullet bullet, DestructibleObstacle obstacle) {}
