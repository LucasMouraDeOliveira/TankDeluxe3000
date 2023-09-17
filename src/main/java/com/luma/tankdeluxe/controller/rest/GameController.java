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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luma.tankdeluxe.dto.ConnectPlayerDTO;
import com.luma.tankdeluxe.dto.CreateGameDTO;
import com.luma.tankdeluxe.dto.GameDTO;
import com.luma.tankdeluxe.entity.User;
import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.Level;
import com.luma.tankdeluxe.game.LevelBuilder;
import com.luma.tankdeluxe.service.GameService;
import com.luma.tankdeluxe.service.UserService;

@RestController
@RequestMapping("games")
public class GameController {

	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService gameService;
	
	@Autowired
	private LevelBuilder levelBuilder;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping
	public ResponseEntity<List<GameDTO>> getGamesList() {
		return ResponseEntity.ok(this.gameService.getGamesInfos());
	}
	
	@PostMapping
	public ResponseEntity<UUID> createGame(CreateGameDTO gameDTO) {
		GameServer newGame;
		
		try {
			Level level = this.levelBuilder.loadLevel(gameDTO.getLevelId());
			newGame = this.gameService.startNewGame(gameDTO.getName(), level);
		} catch (IOException e) {
			logger.error("An error occurred creating new game", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(newGame.getId());
	}
	
	@GetMapping("{gameId}")
	public ResponseEntity<String> getGameMap(@PathVariable UUID gameId) {
		return ResponseEntity.ok(this.gameService.getGame(gameId).getMap());
	}
	
	@GetMapping("{gameId}/level")
	public ResponseEntity<Level> getGameLevel(@PathVariable UUID gameId) {
		return ResponseEntity.ok(this.gameService.getGame(gameId).getLevel());
	}

	@PostMapping("{gameId}/player")
	public ResponseEntity<Void> connectNewPlayer(@PathVariable UUID gameId, @RequestBody ConnectPlayerDTO playerInfos) {
		User user = this.userService.find(playerInfos.getUserId());
		
		this.gameService.connectNewPlayer(gameId, user, playerInfos.getSpecialization());
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{gameId}/player/{userId}")
	public ResponseEntity<Void> respawnPlayer(@PathVariable UUID gameId, @PathVariable UUID userId) {
		
		this.gameService.respawnPlayer(gameId, userId);
		
		return ResponseEntity.ok().build();
	}
}
