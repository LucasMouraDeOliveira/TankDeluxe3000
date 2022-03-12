package com.luma.tankdeluxe.controller;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luma.tankdeluxe.entity.User;
import com.luma.tankdeluxe.service.UserService;


@Controller
@RequestMapping("/joinGame")
public class JoinGameController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("{gameId}")
	public String loadView(Model model, Principal principal, @PathVariable UUID gameId) {
		
		User user = this.userService.find(principal.getName());
		
		model.addAttribute("login", principal.getName());
		model.addAttribute("gameId", gameId);
		model.addAttribute("userId", user.getUuid());
		
		return "joinGame";
	}

}
