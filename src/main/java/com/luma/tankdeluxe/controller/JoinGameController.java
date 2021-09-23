package com.luma.tankdeluxe.controller;

import java.security.Principal;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/joinGame")
public class JoinGameController {
	

	@GetMapping("{gameId}")
	public String loadView(Model model, Principal principal, @PathVariable UUID gameId) {
		
		model.addAttribute("login", principal.getName());
		model.addAttribute("gameId", gameId);
		
		return "joinGame";
	}

}
