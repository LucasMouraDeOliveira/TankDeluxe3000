package com.luma.tankdeluxe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luma.tankdeluxe.dto.actions.PlayerActionDTO;
import com.luma.tankdeluxe.service.MessageService;

@RestController
public class TestController {
	
	@Autowired
	private MessageService messageService;

//	@MessageMapping("actions")
	public void playerActionReceive(PlayerActionDTO actions) {
		System.out.println(">>>" + actions.getAction());
	}
	
	@GetMapping("testGet")
	public ResponseEntity<Void> getTest() {
		this.test();
		return ResponseEntity.ok().build();
	}
	
	@SendTo("/topic/test")
	public String test() {
		System.out.println("SEND TEST ON WEBSOCKET");
		messageService.sendMessage("test", "TEST TEST ====");
		return "TEST";
	}
}
