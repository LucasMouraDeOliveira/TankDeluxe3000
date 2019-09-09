package com.isabo.battletank;

public class SettingsManager {

	public static final int SIZE_RATIO = 10;
	
	// Canvas
	public static final int CANVAS_WIDTH = 800;
	public static final int CANVAS_HEIGHT = 640;
	public static final int OBSTACLE_HEIGHT_PX = 32;
	public static final int OBSTACLE_WIDTH_PX = 32;
	public static final int TANK_WIDTH_PX = 60;
	public static final int TANK_HEIGHT_PX = 76;

	// Simulation
	public static final double WORLD_WIDTH = CANVAS_WIDTH / SIZE_RATIO;
	public static final double WORLD_HEIGHT = CANVAS_HEIGHT / SIZE_RATIO;
	public static final double OBSTACLE_HEIGHT = OBSTACLE_HEIGHT_PX / SIZE_RATIO;
	public static final double OBSTACLE_WIDTH = OBSTACLE_WIDTH_PX / SIZE_RATIO;
	public static final double TANK_WIDTH = TANK_WIDTH_PX / SIZE_RATIO;
	public static final double TANK_HEIGHT = TANK_HEIGHT_PX / SIZE_RATIO;
	
	
	// Player
	public static final int SHOOT_COOLDOWN = 250;
	public static final int MAX_BULLET = 30;
	
	// Bullet
	public static final int BULLET_VELOCITY = 2000;
	public static final int MAX_BOUNCE = 1;
	
	
}
