package com.luma.tankdeluxe.websocket;

import java.io.IOException;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luma.tankdeluxe.dto.PlayerActionDTO;
import com.luma.tankdeluxe.game.player.PlayerSpecialization;
import com.luma.tankdeluxe.service.GameService;

@Component
public class WebSocketEndpoint extends TextWebSocketHandler {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String payload = message.getPayload();
		
		// TOO use only ObjectMapper
		JSONObject object = new JSONObject(payload);
		
		UUID gameId = UUID.fromString(object.getString("gameId"));
		
		// TODO handle "connection" message, then add session to gameServer
		
		if(object.has("type")) {
			if(object.get("type").equals("initializePlayer")) {
				// Initialize new payer
				this.gameService.connectNewPlayer(gameId, object.getString("name"), PlayerSpecialization.valueOf(object.getString("specialization")), session);
				
				session.getAttributes().put("gameId", gameId);
			} else if(object.get("type").equals("respawn")) {
				// Respawn player
				this.gameService.respawnPlayer(gameId, session);
			}
		} else {
			try {
				this.gameService.updatePlayerAction(gameId, session, this.mapper.readValue(payload, PlayerActionDTO.class));
			} catch (IOException e) {
				// TODO
				e.printStackTrace();
			}
		}
		
	}
	
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		this.gameService.connectNewPlayer(gameId, session);
//	}
	

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		UUID gameId = (UUID) session.getAttributes().get("gameId");
		
		this.gameService.disconnectPlayer(gameId, session);
	}

}
