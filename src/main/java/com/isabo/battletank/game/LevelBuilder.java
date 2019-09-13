package com.isabo.battletank.game;

import java.util.ArrayList;
import java.util.List;

import com.isabo.battletank.SettingsManager;

public class LevelBuilder {
	
	boolean [][] level = new boolean[][] {{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},{true,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,true,true},{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,true,true,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,true,true,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,true,true,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,true,true,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,true,false,false,false,true,false,false,false,false,true,false,false,false,false,false,false,true,false,false,false,true},{true,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,true},{true,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,true},{true,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,true},{true,false,false,false,true,false,false,false,true,false,false,false,false,true,false,false,false,false,false,false,true,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,true,true,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,true,true,false,false,false,false,false,false,true},{true,false,false,false,true,true,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,true,true,false,false,true,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,true,true},{true,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true},{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}};
	
	public List<Wall> getNewBorderedLevel() {
		List<Wall> walls = new ArrayList<>();
		int width = (int) (SettingsManager.WORLD_WIDTH / SettingsManager.OBSTACLE_WIDTH);
		int height = (int) (SettingsManager.WORLD_HEIGHT / SettingsManager.OBSTACLE_HEIGHT);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(i == 0 || j == 0 || i == width - 1 || j == height - 1) {
					walls.add(createWall(i * SettingsManager.OBSTACLE_WIDTH, j * SettingsManager.OBSTACLE_HEIGHT));
				}
			}
		}
		
		return walls;
	}
	
	public Level getSpecialLevel() {
		List<Wall> walls = new ArrayList<>();
		Level specialLevel = new Level();
		
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[0].length; j++) {
				if(this.level[i][j]) {
					walls.add(createWall(i * SettingsManager.OBSTACLE_WIDTH, j * SettingsManager.OBSTACLE_HEIGHT));
				}
			}
		}
		
		specialLevel.setObstalces(walls);
		
		specialLevel.addSpone(new Coordinate(10,9));
		specialLevel.addSpone(new Coordinate(60,9));
		specialLevel.addSpone(new Coordinate(110,9));
		
		specialLevel.addSpone(new Coordinate(10,72));
		specialLevel.addSpone(new Coordinate(60,72));
		specialLevel.addSpone(new Coordinate(110,72));
		
		return specialLevel;
	}
	
	public Wall createWall(double x, double y) {
		return new Wall(x, y);
	}
	
	public void addRectangle(List<Wall> walls, int x1, int y1, int x2, int y2) {
		int width = (int) (SettingsManager.WORLD_WIDTH / SettingsManager.OBSTACLE_WIDTH);
		int height = (int) (SettingsManager.WORLD_HEIGHT / SettingsManager.OBSTACLE_HEIGHT);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(i >= x1 && i <= x2 && j >= y1 && j <= y2) {
					walls.add(this.createWall(i * SettingsManager.OBSTACLE_WIDTH, j * SettingsManager.OBSTACLE_HEIGHT));
				}
			}
		}
	}
}
