package com.isabo.battletank.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isabo.battletank.game.GameServer;

@Controller
@RequestMapping("/joinGame")
public class JoinGameController {
	
	@Autowired
	private GameServer gameServer;
	
	@GetMapping
	public String loadView(Model model, Principal principal) {
		model.addAttribute("login", principal.getName());
		
		if(gameServer.getLevel() == null) {
			try {
				this.gameServer.loadLevel("Crossfire");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "joinGame";
	}

}
