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
			if(p.equals(point.getBody1()) && point.getBody2() instanceof Bullet ||
				p.equals(point.getBody2()) && point.getBody1() instanceof Bullet) {
				this.gameServer.killPlayer(p);
				break;
			}
		}
		
		return super.begin(point);
	}


}
