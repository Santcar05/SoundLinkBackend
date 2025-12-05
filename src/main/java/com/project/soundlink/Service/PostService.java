package com.project.soundlink.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.project.soundlink.Entity.Post;
import com.project.soundlink.Repository.PostRepository;
import com.project.soundlink.dto.PostMessage;
import com.project.soundlink.dto.UserDTO;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;  // Para enviar WebSocket

    // Add methods to handle Post operations (CRUD) here
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post savePost(Post post) {
        System.out.println("📥 Guardando post: " + post.getTitle());

        // 1. Guardar en la base de datos
        Post savedPost = postRepository.save(post);
        System.out.println("✅ Post guardado con ID: " + savedPost.getId());

        // 2. Notificar a todos los clientes WebSocket
        notifyNewPost(savedPost);

        return savedPost;
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // ✅ Convertir Post entity a PostMessage DTO completo
    private void notifyNewPost(Post post) {
        try {
            // Crear UserDTO
            UserDTO userDTO = UserDTO.builder()
                    .id(post.getUser().getId())
                    .name(post.getUser().getName())
                    .email(post.getUser().getEmail())
                    .avatarUrl(post.getUser().getAvatarUrl())
                    .verified(post.getUser().getVerified())
                    .build();

            // Crear PostMessage completo
            PostMessage postMessage = PostMessage.builder()
                    .id(post.getId())
                    .user(userDTO)
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .tags(post.getTags())
                    .likes(post.getLikes())
                    .comments(post.getComments())
                    .shares(post.getShares())
                    .timestamp(post.getTimestamp())
                    .build();

            System.out.println("📤 Enviando notificación WebSocket a /topic/feed");
            System.out.println("   Post ID: " + postMessage.getId());
            System.out.println("   Title: " + postMessage.getTitle());
            System.out.println("   User: " + postMessage.getUser().getName());

            messagingTemplate.convertAndSend("/topic/feed", postMessage);

            System.out.println("✅ Notificación WebSocket enviada exitosamente");
        } catch (Exception e) {
            System.err.println("❌ Error enviando notificación WebSocket: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
