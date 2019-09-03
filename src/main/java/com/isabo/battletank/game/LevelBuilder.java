package com.isabo.battletank.game;

import org.springframework.stereotype.Service;

import com.isabo.battletank.SettingsManager;

@Service
public class LevelBuilder {

	public boolean[][] getNewEmptyLevel() {
		return new boolean[SettingsManager.CANVAS_WIDTH / SettingsManager.OBSTACLE_WIDTH][SettingsManager.CANVAS_HEIGHT / SettingsManager.OBSTACLE_HEIGHT];
	}
	
	public boolean [][] getNewBorderedLevel() {
		boolean [][] level = this.getNewEmptyLevel();
		
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[0].length; j++) {
				if(i == 0 || j == 0 || i == level.length - 1 || j == level[0].length - 1) {
					level[i][j] = true;
				}
			}
		}
		
		return level;
	}
}
