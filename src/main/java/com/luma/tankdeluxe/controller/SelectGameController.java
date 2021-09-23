package com.luma.tankdeluxe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("selectGame")
public class SelectGameController {

	@GetMapping
	public String loadSelectGame() {
		
		return "selectGame";
	}
	
}
