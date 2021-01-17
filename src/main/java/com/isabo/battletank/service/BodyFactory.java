package com.isabo.battletank.service;

import org.springframework.stereotype.Service;

import com.isabo.battletank.game.physical.Obstacle;
import com.isabo.battletank.game.physical.Square;

@Service
public class BodyFactory {

	public Obstacle buildObstacle(String code, double x, double y) {
		if(code.equals("0015")) {
			return new Square(x, y);
		}
		
		throw new IllegalArgumentException("Unkown code "+ code +". Can't build associated body.");
	}
	
}
