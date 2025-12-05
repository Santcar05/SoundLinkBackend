package com.project.soundlink.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Its important the order of the annotations (builder first)
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private List<String> tags;
    private Integer likes;
    private Integer comments;
    private Integer shares;
    private Long timestamp;

    @ManyToOne
    private User user;

    public Post(String title, String description, List<String> tags, Integer likes, Integer comments,
            Integer shares, Long timestamp, User user) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
        this.timestamp = timestamp;
        this.user = user;
    }

}
