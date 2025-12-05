package com.project.soundlink;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration // Indica que esta clase contiene configuraciones de Spring
@EnableWebSocketMessageBroker // Habilita el soporte de WebSocket con STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Soporte con STOMP sobre WebSocket
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // Permitir desde cualquier origen
                .withSockJS(); // Fallback para navegadores que no soportan WebSocket

        // También agrega un endpoint sin SockJS para clientes nativos
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue"); // para enviar
        registry.setApplicationDestinationPrefixes("/app"); // para recibir
    }
}
