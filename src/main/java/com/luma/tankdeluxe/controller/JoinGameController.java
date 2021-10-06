package com.luma.tankdeluxe.controller;

import java.security.Principal;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/joinGame")
public class JoinGameController {
	

	@GetMapping("{gameId}")
	public String loadView(Model model, Principal principal, Authentication authentication, @PathVariable UUID gameId) {
		WebAuthenticationDetails authDetails = (WebAuthenticationDetails) authentication;
		
		model.addAttribute("login", principal.getName());
		model.addAttribute("gameAccessToken", authDetails.getSessionId());
		model.addAttribute("gameId", gameId);
		
		return "joinGame";
	}

}
