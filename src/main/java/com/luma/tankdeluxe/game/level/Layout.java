package com.luma.tankdeluxe.game.level;

import java.util.ArrayList;
import java.util.List;

import com.luma.tankdeluxe.game.Cell;

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
