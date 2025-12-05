package com.project.soundlink.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PostMessage {

    private Long id;
    private UserDTO user;  // Objeto completo de usuario
    private String title;  // Separado en lugar de content
    private String description;  // Separado
    private List<String> tags;  // Tags
    private Integer likes;
    private Integer comments;
    private Integer shares;
    private Long timestamp;
}
