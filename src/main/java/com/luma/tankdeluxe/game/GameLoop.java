package com.luma.tankdeluxe.game;

import java.util.ArrayList;
import java.util.List;

import com.luma.tankdeluxe.game.actions.DashAction;
import com.luma.tankdeluxe.game.actions.GameUpdate;
import com.luma.tankdeluxe.game.actions.MovePlayerAction;
import com.luma.tankdeluxe.game.actions.ShootAction;
import com.luma.tankdeluxe.game.actions.UpdatePlayerAction;
import com.luma.tankdeluxe.game.actions.UpdatePlayerStateAction;

public class GameLoop extends Thread {
	
	private int delay;
	
	private GameServer gameServer;
	
	private boolean gameFinished;
	
	private List<GameUpdate> updates;
	
	public GameLoop(GameServer gameServer, int delay) {
		this.gameServer = gameServer;
		this.gameFinished = false;
		this.delay = delay;
		this.initUpdates();
	}
	
	private void initUpdates() {
		this.updates = new ArrayList<>();
		this.updates.add(new UpdatePlayerStateAction(gameServer));
		this.updates.add(new UpdatePlayerAction(gameServer));
		this.updates.add(new MovePlayerAction(gameServer));
		this.updates.add(new ShootAction(gameServer));
		this.updates.add(new DashAction(gameServer));
	}
	
	@Override
	public void run() {
		long start = 0;
		long lastTickDuration = delay;
		
		while(!isGameFinished()) {
			start = System.currentTimeMillis();
			
			update((int) lastTickDuration);
			notifyPlayers();
			
			try {
				Thread.sleep(Math.max(0, delay - (System.currentTimeMillis() - start)));
			} catch(Exception e) {};

			lastTickDuration = System.currentTimeMillis() - start;
		}
	}
	
	private void update(int lastTickDuration) {
		this.gameServer.updateWorld(lastTickDuration);
		for(GameUpdate update : updates) {
			update.act(lastTickDuration);
		}
	}
	
	private void notifyPlayers() {
		/*new Thread(() -> */gameServer.notifyPlayers()/*).start()*/;
	}
	
	private boolean isGameFinished() {
		return this.gameFinished;
	}

}
