package com.luma.tankdeluxe.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luma.tankdeluxe.dto.CreateGameDTO;
import com.luma.tankdeluxe.dto.GameDTO;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.service.GameService;

@RestController
@RequestMapping("games")
public class GameController {

	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService gameService;
	
	@GetMapping("list")
	public ResponseEntity<List<GameDTO>> getGamesList() {
		return ResponseEntity.ok(this.gameService.getGamesInfos());
	}
	
	@PostMapping
	public ResponseEntity<UUID> createGame(CreateGameDTO gameDTO) {
		GameServer newGame;
		
		try {
			newGame = this.gameService.startNewGame(gameDTO.getName());
		} catch (IOException e) {
			logger.error("An error occurred creating new game", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(newGame.getId());
	}
}
