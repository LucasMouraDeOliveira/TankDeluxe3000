package com.isabo.battletank.game.level;

import java.util.ArrayList;
import java.util.List;

import com.isabo.battletank.game.Cell;

public class Layout {
	
	private List<Cell> cells;
	
	private int zIndex;

	
	public Layout(int zIndex) {
		this.zIndex = zIndex;
		this.cells = new ArrayList<>();
	}
	
	
	public List<Cell> getCells() {
		return cells;
	}
	public void addCell(Cell cell) {
		this.cells.add(cell);
	}
	public int getzIndex() {
		return zIndex;
	}
	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	
}
