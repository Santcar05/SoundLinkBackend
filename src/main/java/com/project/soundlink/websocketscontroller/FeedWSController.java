package com.project.soundlink.websocketscontroller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.project.soundlink.dto.PostMessage;

// Creación de 1 controlador WebSocket por “tipo de evento en tiempo real”, no por entidad.
@Controller
public class FeedWSController {

    // Cuando un cliente envía /app/posts/create
    @MessageMapping("/posts/create") // mapea el destino
    @SendTo("/topic/feed") // destino al que se envía la respuesta
    public PostMessage createPost(PostMessage post) {

        return post; // Esto se enviará a todos los suscritos al feed
    }

    // Cuando un cliente envía /app/posts/like
    @MessageMapping("/posts/like")
    @SendTo("/topic/feed-likes")
    public PostMessage likePost(PostMessage post) {
        return post; // Esto se enviará a todos los suscritos a feed-likes
    }
}
