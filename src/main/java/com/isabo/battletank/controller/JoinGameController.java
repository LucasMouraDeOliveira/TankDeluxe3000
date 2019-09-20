package com.isabo.battletank.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/joinGame")
public class JoinGameController {
	
	@GetMapping
	public String loadView(Model model, Principal principal) {
		model.addAttribute("login", principal.getName());
		return "joinGame";
	}

}
