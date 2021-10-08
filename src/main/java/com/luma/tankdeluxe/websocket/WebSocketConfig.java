package com.luma.tankdeluxe.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.luma.tankdeluxe.websocket.handler.InitializePlayerHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	@Autowired
	private WebSocketEndpoint webSocketHandler;
	
	@Autowired
	private InitializePlayerHandler initializePlayerHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/ws");
		registry.addHandler(initializePlayerHandler, "/initializePlayer");
	}

}