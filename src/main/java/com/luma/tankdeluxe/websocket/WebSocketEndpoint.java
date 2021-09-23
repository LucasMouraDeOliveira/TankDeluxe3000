package com.luma.tankdeluxe.websocket;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.luma.tankdeluxe.game.GameServer;
import com.luma.tankdeluxe.game.player.PlayerSpecialization;
import com.luma.tankdeluxe.service.GameService;

@Component
public class WebSocketEndpoint extends TextWebSocketHandler {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private GameServer gameServer;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String payload = message.getPayload();
		JSONObject object = new JSONObject(payload);
		
		// TODO handle "connection" message, then add session to gameServer
		
		if(object.has("type")) {
			if(object.get("type").equals("initializePlayer")) {
				// Initialize new payer
				// TODO handle gameId
				this.gameService.connectNewPlayer(null, object.getString("name"), PlayerSpecialization.valueOf(object.getString("specialization")), session);
			} else if(object.get("type").equals("respawn")) {
				// Respawn player
				// TODO handle gameId
				this.gameService.respawnPlayer(null, session);
			}
		} else {
			gameServer.updatePlayerAction(gameServer.getPlayer(session), object);
		}
		
	}
	
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		this.gameService.connectNewPlayer(gameId, session);
//	}
	

//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		gameServer.removePlayer(session);
//	}

}
