package com.isabo.battletank.service;

import org.springframework.stereotype.Service;

import com.isabo.battletank.game.physical.Obstacle;
import com.isabo.battletank.game.physical.RightTriangle;
import com.isabo.battletank.game.physical.Square;

@Service
public class BodyFactory {

	public Obstacle buildObstacle(String code, double x, double y) {
		if(code.equals("0015")) {
			return new Square(x, y);
		} else if(code.equals("0017")) {
			return new RightTriangle(x, y, Math.PI);
		} else if(code.equals("0018")) {
			return new RightTriangle(x, y, -Math.PI / 2);
		} else if(code.equals("0019")) {
			return new RightTriangle(x, y, Math.PI / 2);
		} else if(code.equals("001A")) {
			return new RightTriangle(x, y, 0);
		}
		
		throw new IllegalArgumentException("Unkown code "+ code +". Can't build associated body.");
	}
	
}
