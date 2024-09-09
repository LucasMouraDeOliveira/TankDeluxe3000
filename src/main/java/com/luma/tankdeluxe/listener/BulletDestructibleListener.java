package com.luma.tankdeluxe.listener;

import java.util.Optional;
import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;

import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.MapEvent;
import com.luma.tankdeluxe.game.level.Cell;
import com.luma.tankdeluxe.game.physical.DestructibleObstacle;

public class BulletDestructibleListener extends ContactAdapter {

    private GameServer gameServer;

    public BulletDestructibleListener(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public boolean begin(ContactPoint point) {

        Optional<Contact> entities = this.getContact(point);
        if (entities.isPresent()) {
            entities.get().bullet().setRemainingBounce(-1);

            DestructibleObstacle obstacle = entities.get().obstacle();
            obstacle.destroy();

            Cell cell = obstacle.getCell();
            cell.setCode("0026");
            this.addMapEvent(cell);
        }

        return super.begin(point);

    }

    private void addMapEvent(Cell cell) {
        this.gameServer.getMapEvents().add(new MapEvent(cell.getX(), cell.getY(), cell.getCode()));
    }

    private Optional<Contact> getContact(ContactPoint point) {
        return point.getBody1() instanceof Bullet bullet && point.getBody2() instanceof DestructibleObstacle obstacle
                ? Optional.of(new Contact(bullet, obstacle))
                : point.getBody2() instanceof Bullet bullet && point.getBody1() instanceof DestructibleObstacle obstacle
                        ? Optional.of(new Contact(bullet, obstacle))
                        : Optional.empty();
    }

}

record Contact(Bullet bullet, DestructibleObstacle obstacle) {
}
