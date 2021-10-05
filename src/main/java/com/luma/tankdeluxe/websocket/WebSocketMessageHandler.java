package com.luma.tankdeluxe.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.luma.tankdeluxe.dto.actions.PlayerActionDTO;
import com.luma.tankdeluxe.service.GameService;

@Controller
public class WebSocketMessageHandler {

	@Autowired
	private GameService gameService;
	
	@MessageMapping("actions")
	public void playerActionReceive(PlayerActionDTO actions) {
		System.out.println(">>>" + actions);
		this.gameService.updatePlayerAction(actions.getGameId(), session, actions);
	}
	
}
