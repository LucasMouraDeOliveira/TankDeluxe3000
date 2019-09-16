package com.isabo.battletank.listener;

import org.dyn4j.dynamics.contact.ContactAdapter;
import org.dyn4j.dynamics.contact.ContactPoint;

import com.isabo.battletank.game.Bullet;
import com.isabo.battletank.game.GameServer;
import com.isabo.battletank.game.Player;

public class TankBulletListener extends ContactAdapter {
	
	private GameServer gameServer;
	
	public TankBulletListener(GameServer gs) {
		this.gameServer = gs;
	}   
	
 	@Override
	public boolean begin(ContactPoint point) {
		for (Player p : this.gameServer.getPlayers()) {
			// If already dead
			if(!p.isAlive()) {
				continue;
			}
			
			if(p.equals(point.getBody1()) && point.getBody2() instanceof Bullet ||
				p.equals(point.getBody2()) && point.getBody1() instanceof Bullet) {
				Bullet b = (point.getBody1() instanceof Bullet ? (Bullet) point.getBody1() : (Bullet) point.getBody2());
				
				this.gameServer.removeBullet(b);
				b.getShooter().getBullets().remove(b);

				// If player has shield, do nothing except decrease shield count
				if(p.getNbShield() > 0) {
					p.removeOneShield();
				} else {
					this.gameServer.killPlayer(p);

					//If another player killed him, he's score increases
					if(!p.equals(b.getShooter())) {
						this.gameServer.getGameScore().increaseScore(b.getShooter());
					}
					
				}
				
				break;
			}
		}
		
		return super.begin(point);
	}


}
