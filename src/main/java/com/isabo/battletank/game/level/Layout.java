package com.isabo.battletank.game.level;

import java.util.ArrayList;
import java.util.List;

import com.isabo.battletank.game.Cell;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Layout {
	
	private List<Cell> cells;
	
	private int zIndex;
	
	public Layout(int zIndex) {
		this.zIndex = zIndex;
		this.cells = new ArrayList<>();
	}
	
	public void addCell(Cell cell) {
		this.cells.add(cell);
	}
	
}
