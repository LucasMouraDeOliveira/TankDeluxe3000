package com.isabo.battletank.game;

import java.util.ArrayList;
import java.util.List;

import com.isabo.battletank.game.level.Layout;

public class Level {
	
	private int width;
	
	private int height;
	
	private List<Coordinate> spawn;
	
	private List<Layout> layouts;
	
	
	public Level() {
		this.spawn = new ArrayList<>();
		this.layouts = new ArrayList<>(10);
	}

	
	public void addLayout(Layout layout) {
		this.layouts.add(layout.getzIndex(), layout);
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
	public void addSpawn(Coordinate c) {
		this.spawn.add(c);
	}
	public void removeSpawn(Coordinate c) {
		this.spawn.remove(c);
	}
	public List<Coordinate> getSpawn() {
		return spawn;
	}
	public List<Layout> getLayouts() {
		return this.layouts;
	}
}
