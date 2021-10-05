package com.luma.tankdeluxe.game.actions;

import java.util.ArrayList;
import java.util.List;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.Bullet;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.player.Player;

public class ShootAction extends GameUpdate {
	
	public ShootAction(GameServer gameServer) {
		super(gameServer);
	}

	@Override
	public void act(int delta) {
		removeUsedBullets();
		createNewBullets(delta);
	}

    private void removeUsedBullets() {
        // The remove action needs to be performed in two step
        // because altering an ArrayList in same time of iterating on it
        // is a bad idea.
        List<Bullet> bulletToRemove = new ArrayList<>();
		for (Bullet b : this.gameServer.getBullets()) {
			if(b.getRemainingBounce() < 0) {
				bulletToRemove.add(b);
			}
		}
		// Remove the bullet
		for (Bullet b : bulletToRemove) {
			super.gameServer.removeBullet(b);
		}
    }
	
    private void createNewBullets(int delta) {
        for (Player p : super.gameServer.getPlayers()) {
            
            if(!p.isAlive() || p.isInvincible()) {
                continue;
            }
            
            if(p.getCooldown() > 0) {
                p.setCooldown(p.getCooldown() - delta);
            }
            
            if(canShoot(p)) {
                if(p.isShooting()) {
                    p.addCharge(delta);
                } else if(hasStopedCharging(p)) {
                    makeShoot(p);
                }
            }
        }
    }
    
	private boolean canShoot(Player p) {
		return p.getMaxBullet() > p.getBullets().size() && p.getCooldown() <= 0;
	}
	
	private boolean hasStopedCharging(Player player) {
		return player.getCharge() > 0;
	}

	private void makeShoot(Player p) {
		Bullet newBullet = new Bullet(p);

		// Update velocity according to charge
		if(p.getCharge() > 1000) {
			int bulletVelocity = p.getBulletVelocity();

			if(p.getCharge() < 2000) {
				bulletVelocity *= 2;
			} else {
				bulletVelocity *= 3;
			}

			newBullet.setVelocity(bulletVelocity);
		}
		
		
		super.gameServer.addBullet(newBullet);
		p.addBullet(newBullet);
		
		p.setCooldown(SettingsManager.SHOOT_COOLDOWN);
		p.setCharge(0);
	}
}
