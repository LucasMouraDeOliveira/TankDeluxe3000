package com.luma.tankdeluxe.game;

import java.util.HashMap;
import java.util.Map;

import com.luma.tankdeluxe.game.player.Player;
import com.luma.tankdeluxe.service.LeaderboardService;

public class GameScore {
	
	private Map<Player, Integer> scores;
	
	private int allTimeHighScore;
	
	private Player bestPlayer;
	
	private LeaderboardService leaderboardService;
	
	public GameScore(LeaderboardService leaderboardService) {
		this.scores = new HashMap<>();
		this.allTimeHighScore = 0;
		this.leaderboardService = leaderboardService;
	}
	
	public void initScore(Player player) {
		this.scores.put(player, 0);
	}
	
	public void increaseScore(Player player) {
		if(this.scores.containsKey(player)) {
			int playerScore = this.scores.get(player);
			
			playerScore++;
			this.scores.put(player, playerScore);

			if(playerScore % 3 == 0) {
				player.addShield();
			}
			
			// Publish score to leaderboard
			this.leaderboardService.addScoreEntryIfEligible(player.getName(), playerScore);
		}
		
		
		this.calculateAllTimeHigh();
	}

	private void calculateAllTimeHigh() {
		for(Map.Entry<Player, Integer> entry : this.scores.entrySet()) {
			if(entry.getValue() > allTimeHighScore) {
				allTimeHighScore = entry.getValue();
				bestPlayer = entry.getKey();
			}
		}
	}
	
	
	public int getAllTimeHighScore() {
		return allTimeHighScore;
	}
	
	public Player getBestPlayer() {
		return bestPlayer;
	}
	
	public Map<Player, Integer> getScores() {
		return scores;
	}

	public void removeScore(Player player) {
		this.scores.remove(player);
		
	}

}
