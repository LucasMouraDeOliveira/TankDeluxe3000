package com.isabo.battletank.game;

import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.springframework.stereotype.Service;

import com.isabo.battletank.SettingsManager;

@Service
public class LevelBuilder {

	public List<Body> getNewBorderedLevel() {
		List<Body> walls = new ArrayList<>();
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
	
	private Body createWall(double x, double y) {
		Body wall = new Body();
		wall.addFixture(Geometry.createRectangle(SettingsManager.OBSTACLE_WIDTH, SettingsManager.OBSTACLE_HEIGHT), 1, 0.2, 0);
		wall.translate(x + SettingsManager.OBSTACLE_WIDTH / 2D, y + SettingsManager.OBSTACLE_HEIGHT / 2D);
		System.out.println(wall.getWorldCenter());
		wall.setMass(MassType.INFINITE);
		return wall;
	}
}
