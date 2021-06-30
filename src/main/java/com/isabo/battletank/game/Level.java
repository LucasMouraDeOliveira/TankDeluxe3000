package com.isabo.battletank.game;

import java.util.ArrayList;
import java.util.List;

import com.isabo.battletank.game.level.Layout;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
		this.layouts.add(layout.getZIndex(), layout);
	}
	
	public void addSpawn(Coordinate c) {
		this.spawn.add(c);
	}
	
	public void removeSpawn(Coordinate c) {
		this.spawn.remove(c);
	}
	
}
