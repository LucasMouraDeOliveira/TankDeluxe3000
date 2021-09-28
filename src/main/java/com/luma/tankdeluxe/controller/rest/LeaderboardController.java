package com.luma.tankdeluxe.controller.rest;

import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luma.tankdeluxe.entity.ScoreEntry;
import com.luma.tankdeluxe.service.LeaderboardService;

@RestController
@RequestMapping("leaderboard")
public class LeaderboardController {

	@Autowired
	private LeaderboardService leaderboardService;
	
	@GetMapping
	public ResponseEntity<SortedSet<ScoreEntry>> getLeaderboard() {
		return ResponseEntity.ok(this.leaderboardService.getLeaderboard());
	}
}
