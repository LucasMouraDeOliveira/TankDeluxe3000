package com.isabo.battletank.entity.dto;

import java.util.List;

public class LevelDTO {
	
	private int width;
	private int height;
	private List<List<String>> ground;
	private List<List<String>> obstacle;
	
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<List<String>> getGround() {
		return ground;
	}
	public void setGround(List<List<String>> ground) {
		this.ground = ground;
	}
	public List<List<String>> getObstacle() {
		return obstacle;
	}
	public void setObstacle(List<List<String>> obstacle) {
		this.obstacle = obstacle;
	}
}
