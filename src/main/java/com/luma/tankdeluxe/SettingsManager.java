package com.luma.tankdeluxe;

public class SettingsManager {

	public static final int FPS = 30;
	public static final int SIZE_RATIO = 10;
	
	// Canvas
	public static final int CANVAS_WIDTH = 1200;
	public static final int CANVAS_HEIGHT = 800;
	public static final int OBSTACLE_HEIGHT_PX = 32;
	public static final int OBSTACLE_WIDTH_PX = 32;
	public static final int TANK_WIDTH_PX = 48;
	public static final int TANK_HEIGHT_PX = 61;

	// Simulation
	public static final double WORLD_WIDTH = (double) CANVAS_WIDTH / SIZE_RATIO;
	public static final double WORLD_HEIGHT = (double) CANVAS_HEIGHT / SIZE_RATIO;
	public static final double OBSTACLE_HEIGHT = (double) OBSTACLE_HEIGHT_PX / SIZE_RATIO;
	public static final double OBSTACLE_WIDTH = (double) OBSTACLE_WIDTH_PX / SIZE_RATIO;
	public static final double TANK_WIDTH = (double) TANK_WIDTH_PX / SIZE_RATIO;
	public static final double TANK_HEIGHT = (double) TANK_HEIGHT_PX / SIZE_RATIO;
	public static final double TANK_VELOCITY = 110;
	
	
	// Player
	public static final int SHOOT_COOLDOWN = 200;
	public static final int MAX_BULLET = 3;
	public static final int SPAWN_INVICIBLE_DURATION = 3;
	public static final double DASH_VELOCITY = 300;
	public static final int DASH_COOLDOWN = 1500;
	
	// Bullet
	public static final int BULLET_VELOCITY = 50;
	public static final int MAX_BOUNCE = 1;
	
	
}
