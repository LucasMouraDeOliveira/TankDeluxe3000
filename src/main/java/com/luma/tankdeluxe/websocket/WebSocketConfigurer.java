package com.luma.tankdeluxe.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigurer implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/message");
    }
	 
   @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
	   registry.addEndpoint("websockets").setAllowedOrigins("*").withSockJS();
//	   registry.addEndpoint("actions").setAllowedOrigins("*").withSockJS();
	}
   
//   @Override
//   public void configureClientOutboundChannel(ChannelRegistration registration) {
//       registration.taskExecutor().corePoolSize(1);
//   }
//
//   @Override
//   public void configureClientInboundChannel(ChannelRegistration registration) {
//       registration.taskExecutor().corePoolSize(1);
//   }
	    
}

