package com.luma.tankdeluxe.listener;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.listener.CollisionListenerAdapter;

import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.player.Player;

public class TankBulletListener extends CollisionListenerAdapter<Body, BodyFixture> {
	
	private GameServer gameServer;
	
	public TankBulletListener(GameServer gs) {
		this.gameServer = gs;
	}   
	
	@Override
	public boolean collision(NarrowphaseCollisionData<Body, BodyFixture> collision) {
		for (Player p : this.gameServer.getPlayers()) {
			// If already dead
			if(!p.isAlive()) {
				continue;
			}
			
			if(p.equals(collision.getBody1()) && collision.getBody2() instanceof Bullet ||
				p.equals(collision.getBody2()) && collision.getBody1() instanceof Bullet) {
				Bullet b = (collision.getBody1() instanceof Bullet ? (Bullet) collision.getBody1() : (Bullet) collision.getBody2());
				
				this.gameServer.removeBullet(b);
				b.getShooter().getBullets().remove(b);

				// If player has shield, do nothing except decrease shield count
				if(p.getNbShield() > 0) {
					p.removeOneShield();
				} else {
					p.setHealth(p.getHealth() - b.getDamage());
					
					if(!p.isInvincible() && p.getHealth() <= 0) {
						this.gameServer.killPlayer(p);
	
						//If another player killed him, he's score increases
						if(!p.equals(b.getShooter())) {
							this.gameServer.getGameScore().increaseScore(b.getShooter());
						}
					}
				}
				
				break;
			}
		}
		return super.collision(collision);
	}

}
