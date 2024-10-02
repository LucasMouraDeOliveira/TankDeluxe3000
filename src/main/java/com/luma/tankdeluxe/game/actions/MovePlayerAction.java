package com.luma.tankdeluxe.game.actions;

import org.dyn4j.geometry.Interval;
import org.dyn4j.geometry.Vector2;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.player.Player;

public class MovePlayerAction extends GameUpdate {

	public MovePlayerAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		double force = SettingsManager.TANK_VELOCITY * delta;
		
		for(Player player : this.gameServer.getPlayers()) {
			
			if(!player.isAlive()) {
				continue;
			}
			
			if(player.getCharge() > 0) {
				force /= 2;
			}
			
			Vector2 r = new Vector2(player.getTransform().getRotation().toRadians() - Math.PI);
			
			if(player.isMoving(Player.NORTH)) {
				Vector2 f = r.product(-force);
				player.applyForce(f);
			} else if(player.isMoving(Player.SOUTH)) {
				Vector2 f = r.product(force);

				player.applyForce(f);
			} 
			
			if(player.isMoving(Player.EAST)) {
				player.applyTorque(SettingsManager.TANK_ROTATION_VELCITY);
			} else if(player.isMoving(Player.WEST)) {
				player.applyTorque(-SettingsManager.TANK_ROTATION_VELCITY);
			}
			
			// If not dashing, make sure the linear velocity is in the direction of the tank front
			if(player.getDashCooldown() <= 0) {
				Vector2 normal = player.getTransform().getTransformedR(new Vector2(1.0, 0.0));
				double defl = player.getLinearVelocity().dot(normal);
				// Clamp the velocity
				defl = Interval.clamp(defl, -200, 200);
				player.setLinearVelocity(normal.multiply(defl));
			}
			
			// Turret move
			Vector2 aimDirection = new Vector2(player.getAimX() - player.getX(), player.getAimY() - player.getY());
			
			player.setTurretAngle(Math.atan2(aimDirection.y, aimDirection.x));
		}
	}

}
