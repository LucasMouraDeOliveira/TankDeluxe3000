package com.luma.tankdeluxe.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luma.tankdeluxe.dto.GameDTO;
import com.luma.tankdeluxe.service.GameService;

@RestController
@RequestMapping("games")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping("list")
	public ResponseEntity<List<GameDTO>> getGamesList() {
		return ResponseEntity.ok(this.gameService.getGamesInfos());
	}
}
