package com.luma.tankdeluxe.service;

import java.util.HashMap;
import java.util.Map;

import org.dyn4j.dynamics.Body;
import org.springframework.stereotype.Service;

import com.luma.tankdeluxe.game.level.Cell;
import com.luma.tankdeluxe.game.physical.DestructibleObstacle;
import com.luma.tankdeluxe.game.physical.RegenerationArea;
import com.luma.tankdeluxe.game.physical.RightTriangle;
import com.luma.tankdeluxe.game.physical.Square;

@Service
public class BodyFactory {

	private static final String SQUARE_WOOD_BLOCK = "0015";
	private static final String BOTTOM_RIGHT_WOOD_BLOCK = "0017";
	private static final String BOTTOM_LEFT_WOOD_BLOCK = "0018";
	private static final String TOP_RIGHT_WOOD_BLOCK = "0019";
	private static final String TOP_LEFT_WOOD_BLOCK = "001A";

	private static final String FULL_CRATE = "0021";
	private static final String DAMAGED_CRATE = "0026";
	private static final String DESTROYED_CRATE = "0027";
	
	private static final String REGENERATION_AREA = "0028";

	public Body buildObstacle(Cell cell, String code, double x, double y) {
		if (code.equals(SQUARE_WOOD_BLOCK)) {
			return new Square(x, y);
		} else if (code.equals(BOTTOM_RIGHT_WOOD_BLOCK)) {
			return new RightTriangle(x, y, Math.PI);
		} else if (code.equals(BOTTOM_LEFT_WOOD_BLOCK)) {
			return new RightTriangle(x, y, -Math.PI / 2);
		} else if (code.equals(TOP_RIGHT_WOOD_BLOCK)) {
			return new RightTriangle(x, y, Math.PI / 2);
		} else if (code.equals(TOP_LEFT_WOOD_BLOCK)) {
			return new RightTriangle(x, y, 0);
		} else if (code.equals(FULL_CRATE)) {
			Map<Integer, String> healthStatus = new HashMap<>();
			healthStatus.put(2, FULL_CRATE);
			healthStatus.put(1, DAMAGED_CRATE);
			healthStatus.put(0, DESTROYED_CRATE);
			return new DestructibleObstacle(cell, x, y, healthStatus);
		} else if(code.equals(REGENERATION_AREA)) {
			return new RegenerationArea(cell, x, y);
		}

		throw new IllegalArgumentException("Unkown code " + code + ". Can't build associated body.");
	}

}
