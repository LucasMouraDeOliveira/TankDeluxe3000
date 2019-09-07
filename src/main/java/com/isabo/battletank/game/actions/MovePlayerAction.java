package com.isabo.battletank.game.actions;

import org.dyn4j.geometry.Vector2;

import com.isabo.battletank.game.GameServer;
import com.isabo.battletank.game.Player;

public class MovePlayerAction extends GameUpdate {

	public MovePlayerAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		double force = 100000 * delta;
		
		for(Player player : this.gameServer.getPlayers()) {
			Vector2 r = new Vector2(player.getTransform().getRotation() + Math.PI * 0.5);
			Vector2 c = player.getWorldCenter();

			if(player.isMoving(Player.NORTH)) {
				Vector2 f = r.product(-force * 10000000);
				player.applyForce(f);
			} else if(player.isMoving(Player.SOUTH)) {
				Vector2 f = r.product(force * 10000000);
				player.applyForce(f);
			} else {
				// Fake friction
				Vector2 v = player.getLinearVelocity();
				player.setLinearVelocity(v.product(0.8));
			}
			
			// Limit angular velocity to 8
			if(player.isMoving(Player.EAST) && Math.abs(player.getAngularVelocity()) < 8) {
	        	Vector2 f1 = r.product(force * 0.03).right();
	        	Vector2 f2 = r.product(force * 0.03).left();
	        	Vector2 p1 = c.sum(r.product(0.9));
	        	Vector2 p2 = c.sum(r.product(-0.9));
	        	
	        	player.applyForce(f1, p1);			// Apply a force to the top going left
	        	player.applyForce(f2, p2);			// Apply a force to the bottom going right
			} else if(player.isMoving(Player.WEST) && Math.abs(player.getAngularVelocity()) < 8) {
				Vector2 f1 = r.product(force * 0.03).left();
	        	Vector2 f2 = r.product(force * 0.03).right();
	        	Vector2 p1 = c.sum(r.product(0.9));
	        	Vector2 p2 = c.sum(r.product(-0.9));
	        	
	        	player.applyForce(f1, p1);			// Apply a force to the top going left
	        	player.applyForce(f2, p2);			// Apply a force to the bottom going right
			} else {
				// Fake friction
				player.setAngularVelocity(player.getAngularVelocity() * 0.8);
			}
		}
	}

}
