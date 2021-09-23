package com.luma.tankdeluxe.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luma.tankdeluxe.dto.GameDTO;
import com.luma.tankdeluxe.service.GameService;

@Controller
@RequestMapping("/joinGame")
public class JoinGameController {
	
	private static final Logger logger = LoggerFactory.getLogger(JoinGameController.class);
	
	@Autowired
	private GameService gameService;
	

	@GetMapping
	public String loadView(Model model, Principal principal) {
		model.addAttribute("login", principal.getName());
		
		UUID gameIdToJoin = null;
		
		List<GameDTO> games = this.gameService.getGamesInfos();
		
		if(games.isEmpty()) {
			try {
				gameIdToJoin = this.gameService.startNewGame("Main Game").getId();
			} catch (IOException e) {
				logger.error("An error occurred starting a new game", e);
				return "error";
			}
		} else {
			gameIdToJoin = games.get(0).getGameId();
		}
		
		model.addAttribute("gameId", gameIdToJoin);
		
		return "joinGame";
	}

}
