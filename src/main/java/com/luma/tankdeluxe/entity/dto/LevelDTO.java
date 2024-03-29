package com.luma.tankdeluxe.entity.dto;

import java.util.List;

public class LevelDTO {
	
	private String name;
	private int width;
	private int height;
	private List<List<String>> ground;
	private List<List<String>> obstacle;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
