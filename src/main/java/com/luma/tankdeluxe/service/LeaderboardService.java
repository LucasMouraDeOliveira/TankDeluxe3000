package com.luma.tankdeluxe.service;

import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.luma.tankdeluxe.entity.ScoreEntry;
import com.luma.tankdeluxe.repository.ScoreEntryRepository;

@Service
public class LeaderboardService {

	@Autowired
	private ScoreEntryRepository scoreEntryRepository;
	
	@Value("${app.score.max-kept}")
	private int maxKept;
	
	
	public SortedSet<ScoreEntry> getLeaderboard() {
		TreeSet<ScoreEntry> scores = new TreeSet<>();
		
		this.scoreEntryRepository.findAll().forEach(scores::add);
		
		return scores;
	}
	
	public void addScoreEntryIfEligible(String username, int score) {
		SortedSet<ScoreEntry> scores = this.getLeaderboard();
		
		if(scores.size() >= this.maxKept && scores.last().getScore() > score) {
			// Not enough to be added to leaderboard
			return;
		}
		
		this.createScoreEntry(username, score);

		// Remove last score entry if max reached
		if(scores.size() >= this.maxKept) {
			this.scoreEntryRepository.delete(scores.last());
		}
	}
	
	public ScoreEntry createScoreEntry(String username, int score) {
		ScoreEntry newEntry = new ScoreEntry();
		
		newEntry.setUsername(username);
		newEntry.setScore(score);
		
		return this.saveScoreEntry(newEntry);
	}
	
	public ScoreEntry saveScoreEntry(ScoreEntry entry) {
		return this.scoreEntryRepository.save(entry);
	}
}
