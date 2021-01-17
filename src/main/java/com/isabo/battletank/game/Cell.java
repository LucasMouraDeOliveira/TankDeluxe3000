package com.isabo.battletank.game;

import org.dyn4j.dynamics.Body;

public class Cell {
	
	private final int x;
	
	private final int y;
	
	private String code;
	
	private Body body;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public boolean hasBody() {
		return body != null;
	}

}
