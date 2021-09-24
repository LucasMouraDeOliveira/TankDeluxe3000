package com.luma.tankdeluxe.game;

import java.util.ArrayList;
import java.util.List;

import com.luma.tankdeluxe.SettingsManager;

public class TextFileLevelBuilder {
	
//	public Level loadLevel(Path levelFolder) {
//		try {
//			Level level = new Level();
//			loadFloor(level, Files.readAllLines(levelFolder.resolve("floor.txt")));
//			loadWallsAndSpawns(level, Files.readAllLines(levelFolder.resolve("walls.txt")));
//			return level;
//		} catch(IOException e) {
//			return new Level();
//		}
//	}

	private void loadFloor(Level level, List<String> lines) {
//		level.setCells(loadCells(lines));
	}
	
	private List<Cell> loadCells(List<String> lines) {
		List<Cell> cells = new ArrayList<>();
		int row = 0;
		for(String line : lines) {
			int col = 0;
			for(char c : line.toCharArray()) {
				cells.add(createCell(col, row, c));
				col++;
			}
			row++;
		}
		return cells;
	}
	
	private Cell createCell(int col, int row, char c) {
		Cell cell = new Cell(col, row);
//		cell.setFloorId(Integer.valueOf(c + ""));
		return cell;
	}
	
	private void loadWallsAndSpawns(Level level, List<String> lines) {
		addWalls(level, lines);
		addSpawns(level, lines);
	}
	
	private void addWalls(Level level, List<String> lines) {
		int row = 0;
		int w = lines.get(0).length();
		for(String line : lines) {
			int col = 0;
			for(char c : line.toCharArray()) {
				if(c == '1') {
//					Cell cell = getCell(level, col, row, w);
//					cell.setWall(new Square(col * SettingsManager.OBSTACLE_WIDTH, row * SettingsManager.OBSTACLE_HEIGHT));
				}
				col++;
			}
			row++;
		}
	}
	
//	private Cell getCell(Level level, int col, int row, int w) {
////		return level.getCells().get(row * w + col);
//	}

	private void addSpawns(Level level, List<String> lines) {
		int row = 0;
		for(String line : lines) {
			int col = 0;
			for(char c : line.toCharArray()) {
				if(c == '2') {
					level.addSpawn(new Coordinate((int)(col * SettingsManager.OBSTACLE_WIDTH), (int)(row * SettingsManager.OBSTACLE_HEIGHT)));
				}
				col++;
			}
			row++;
		}
	}

}