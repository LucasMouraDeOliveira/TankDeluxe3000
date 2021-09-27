package com.luma.tankdeluxe.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luma.tankdeluxe.game.LevelBuilder;

@Controller
@RequestMapping("selectGame")
public class SelectGameController {

	@Autowired
	private LevelBuilder levelBuilder;
	
	@GetMapping
	public String loadSelectGame(Map<String, Object> model) {
		
		model.put("maps", this.levelBuilder.getMapList());
		
		return "selectGame";
	}
	
}
