package com.project.soundlink.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.soundlink.Entity.Story;
import com.project.soundlink.Repository.StoryRepository;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    // Add service methods here to interact with StoryRepository
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    public Story getStoryById(Long id) {
        return storyRepository.findById(id).orElse(null);
    }

    public Story saveStory(Story story) {
        return storyRepository.save(story);
    }

    public void deleteStory(Story story) {
        storyRepository.delete(story);
    }

    public Story updateStory(Long id, Story story) {
        story.setId(id);
        return storyRepository.save(story);
    }

}
