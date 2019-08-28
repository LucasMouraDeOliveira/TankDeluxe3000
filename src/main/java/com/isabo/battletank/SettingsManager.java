package com.isabo.battletank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SettingsManager {
	
	@Value("${bullet.velocity}")
	private int bulletVelocity;
	
	public int getBulletVelocity() {
		return this.bulletVelocity;
	}
}
