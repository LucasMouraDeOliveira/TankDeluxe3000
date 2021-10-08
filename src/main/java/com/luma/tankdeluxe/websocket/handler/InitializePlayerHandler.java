package com.luma.tankdeluxe.websocket.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luma.tankdeluxe.service.GameService;

public class InitializePlayerHandler extends TextWebSocketHandler {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO create player
	}

}
