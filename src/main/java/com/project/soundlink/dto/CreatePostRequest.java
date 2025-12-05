package com.project.soundlink.dto;

import java.util.List;

public class CreatePostRequest {

    public Long userId;
    public String title;
    public String description;
    public List<String> tags;
    public Long timestamp;
}
