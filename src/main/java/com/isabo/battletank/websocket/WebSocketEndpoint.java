package com.isabo.battletank.websocket;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.isabo.battletank.game.GameServer;

@Component
public class WebSocketEndpoint extends TextWebSocketHandler {
	
	private int playerCount = 0;
	
	@Autowired
	private GameServer gameServer;
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String payload = message.getPayload();
		JSONObject object = new JSONObject(payload);
		
		String type = object.getString("type");
		if(type != null && type.equals("name")) {
			gameServer.updatePlayerAction(gameServer.getPlayer(session), object);
		} else {
			gameServer.getPlayer(session).setName(object.getString("name"));
		}
		
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		gameServer.addPlayer(session, "Player " + playerCount);
		if(playerCount == 0) {
			gameServer.start();
		}
		playerCount++;
	}
	

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		gameServer.removePlayer(session);
	}

}
