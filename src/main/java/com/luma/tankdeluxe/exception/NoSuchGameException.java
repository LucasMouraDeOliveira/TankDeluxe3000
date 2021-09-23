package com.luma.tankdeluxe.exception;

import java.util.UUID;

public class NoSuchGameException extends RuntimeException {

	private static final long serialVersionUID = -9199580471821925247L;

	public NoSuchGameException(UUID gameId) {
		super(String.format("Un existing game with id %s", gameId));
	}
}
