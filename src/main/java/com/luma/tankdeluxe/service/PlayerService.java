package com.luma.tankdeluxe.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.dyn4j.geometry.Transform;
import org.springframework.stereotype.Service;

import com.luma.tankdeluxe.SettingsManager;
import com.luma.tankdeluxe.game.Color;
import com.luma.tankdeluxe.game.Coordinate;
import com.luma.tankdeluxe.game.player.Miner;
import com.luma.tankdeluxe.game.player.Player;
import com.luma.tankdeluxe.game.player.PlayerSpecialization;
import com.luma.tankdeluxe.game.player.Quicker;
import com.luma.tankdeluxe.game.player.Shooter;
import com.luma.tankdeluxe.game.player.Sniper;

/**
 * 
 * Manager player manipulation. Need to be thread-safe !
 * @author Matthieu Bellamy
 *
 */
@Service
public class PlayerService {

	
	public Player createPlayer(String name, Color color, PlayerSpecialization specialization) {
		Player newPlayer;
		
		if(specialization == PlayerSpecialization.SHOOTER) {
			newPlayer = new Shooter(name, color);
		} else if(specialization == PlayerSpecialization.MINER) {
			newPlayer = new Miner(name, color);
		} else if(specialization == PlayerSpecialization.SNIPER) {
			newPlayer = new Sniper(name, color);
		} else if(specialization == PlayerSpecialization.QUICKER) {
			newPlayer = new Quicker(name, color);
		} else {
			throw new IllegalArgumentException("Unknwon player speicialization " + specialization);
		}
		
		return newPlayer;
	}
			
	public void initializeStats(Player player) {
		player.setAliveSince(ZonedDateTime.now());
		player.setAlive(true);
		player.setShooting(false);
		player.setDashing(false);
		player.setInvincible(true);
		player.setDashCooldown(0);
		player.setMaxBullet(SettingsManager.MAX_BULLET);
		player.setBulletVelocity(SettingsManager.BULLET_VELOCITY);
		player.setNbShield(0);
		player.setMineCount(0);
		player.setMoving(0, false);
		player.setMoving(1, false);
		player.setMoving(2, false);
		player.setMoving(3, false);
		player.setBullets(new ArrayList<>());
		
		player.applyBuff();
	}
	
	public void initializePlayerPosition(Player player, Coordinate spone) {
		Transform playerTransform = player.getTransform();
		playerTransform.setRotation(0);
		playerTransform.setTranslation(spone.getX(), spone.getY());
	}
	
}
