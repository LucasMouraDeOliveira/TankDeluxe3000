package com.luma.tankdeluxe.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luma.tankdeluxe.dto.PlayerActionDTO;
import com.luma.tankdeluxe.service.GameService;

@Component
public class WebSocketEndpoint extends TextWebSocketHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEndpoint.class);

	private static final String GAME_ID = "gameId";
	private static final String USER_ID = "userId";
	
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		PlayerActionDTO actions;
		
		try {
			actions = this.mapper.readValue(message.getPayload(), PlayerActionDTO.class);
		} catch (IOException e) {
			logger.error("Unable to parse message to PlayerActionDTO", e);
			return;
		}

		Map<String, Object> sessionAttributes = session.getAttributes();
		
		// Store data in session if not present
		sessionAttributes.computeIfAbsent(GAME_ID, k -> actions.getGameId());
		sessionAttributes.computeIfAbsent(USER_ID, k -> actions.getUserId());
		
		this.gameService.updatePlayerAction(actions, session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		UUID gameId = (UUID) session.getAttributes().get(GAME_ID);
		UUID userId = (UUID) session.getAttributes().get(USER_ID);
		
		this.gameService.disconnectPlayer(gameId, userId);
	}

}
