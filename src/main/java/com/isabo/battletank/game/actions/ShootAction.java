package com.isabo.battletank.game.actions;

import java.util.ArrayList;
import java.util.List;

import com.isabo.battletank.SettingsManager;
import com.isabo.battletank.game.Bullet;
import com.isabo.battletank.game.GameServer;
import com.isabo.battletank.game.Player;

public class ShootAction extends GameUpdate {
	
	public ShootAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		
		List<Player> playersToRemove = new ArrayList<>();
		
		// Move existing bullet
//		for (Bullet b : super.gameServer.getBullets()) {
//			boolean hasBounce = false;
//			
//			double vx = b.getVelocity() * Math.sin(b.getAngle());
//			double vy = -b.getVelocity() * Math.cos(b.getAngle());
//			
//			int newX = (int) Math.round(b.getX() + vx);
//			int newY = (int) Math.round(b.getY() + vy);
//
//			int nextBlockX = (int) (newX / SettingsManager.OBSTACLE_WIDTH);
//			int nextBlockY = (int) (newY / SettingsManager.OBSTACLE_HEIGHT);

			// If bullet out of screen, delete it
//			if(	nextBlockX < 0 || nextBlockX > this.gameServer.getLevel().length ||
//				nextBlockY < 0 || nextBlockY > this.gameServer.getLevel()[0].length) {
//				
//				super.gameServer.getBullets().remove(b);
//				return;
//				
//			} else {
				// bounce detection
//				// If this block is an obstacle
//				if(this.gameServer.getLevel()[nextBlockX][nextBlockY]) {
//					Rectangle2D hitBox = new Rectangle2D.Double(
//							nextBlockX * SettingsManager.OBSTACLE_WIDTH,
//							nextBlockY * SettingsManager.OBSTACLE_HEIGHT, 
//							SettingsManager.OBSTACLE_WIDTH,
//							SettingsManager.OBSTACLE_HEIGHT);
					
					// If next  bullet position is in obstacle
//					if(hitBox.contains(new Point2D.Double(newX, newY))) {
						
						// Detect on which side of the obstacle is the bullet
//						if(b.getX() > hitBox.getMaxX() || b.getX() < hitBox.getMinX()) {
//							vx *= -1;
//							b.setAngle(Math.atan2(vy, vx) + (Math.PI / 2));
//							b.bounce();
//							hasBounce = true;
//						} else if(b.getY() > hitBox.getMaxY() || b.getY() < hitBox.getMinY()) {
//							vy *= -1;
//							b.setAngle(Math.atan2(vy, vx) + (Math.PI / 2));
//							b.bounce();
//							hasBounce = true;
//						}
//					}
//				}
//			}
			
			
			// Move
//			if(!hasBounce) {
//				newX = (int) Math.round(b.getX() + vx);
//				newY = (int) Math.round(b.getY() + vy);
//				
//				b.setX(newX);
//				b.setY(newY);
//			}
			
			// Player collision
//			Iterator<Player> iterator = this.gameServer.getPlayers().iterator();
//			Player p = null;
//			while(iterator.hasNext()) {
//				p = iterator.next();
//				
//				Shape hitBox = new Rectangle2D.Double(
//						p.getX() - SettingsManager.TANK_WIDTH / 2, 
//						p.getY() - SettingsManager.TANK_HEIGHT / 2, SettingsManager.TANK_WIDTH, SettingsManager.TANK_HEIGHT);
//				
//				AffineTransform transform = new AffineTransform();
//				
//				transform.rotate(p.getAngle());
//				hitBox = transform.createTransformedShape(hitBox);
//				
//				if(!b.getShooter().equals(p) && hitBox.contains(new Point2D.Double(b.getX(), b.getY()))) {
//					playersToRemove.add(p);
//				}
//			}
//		}
		
//		for(Player p : playersToRemove) {
//			super.gameServer.killPlayer(p);
//		}
		
		
		for (Player p : super.gameServer.getPlayers()) {
			p.setCooldown(p.getCooldown() - delta);

			// Remove old bullet
			
			// The remove action needs to be performed in two step
			// because altering an ArrayList in same time of iterating on it
			// is a bad idea.

			// Find bullet to remove
			List<Bullet> bulletToRemove = new ArrayList<>();
			for (Bullet b : p.getBullets()) {
				if(b.getRemainingBounce() < 0) {
					bulletToRemove.add(b);
				}
			}
			// Remove the bullet
			for (Bullet b : bulletToRemove) {
				p.getBullets().remove(b);
				super.gameServer.removeBullet(b);
			}
			
			// Create new bullet
			if(p.isShooting() && p.getMaxBullet() > p.getBullets().size() && p.getCooldown() <= 0) {
				Bullet newBullet = new Bullet(p);
				
				super.gameServer.addBullet(newBullet);
				p.addBullet(newBullet);
				
				p.setCooldown(SettingsManager.SHOOT_COOLDOWN);
			}
		}
	}
}
