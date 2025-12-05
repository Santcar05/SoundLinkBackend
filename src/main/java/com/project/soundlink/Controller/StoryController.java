package com.project.soundlink.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.soundlink.Entity.Story;
import com.project.soundlink.Service.StoryService;

@RestController
@RequestMapping("/api/stories")
@CrossOrigin(origins = "*")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping("/")
    public ResponseEntity<List<Story>> getStories() {
        List<Story> stories = storyService.getAllStories();
        return ResponseEntity.ok(stories);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Story> getStoryById(@PathVariable Long id) {
        Story story = storyService.getStoryById(id);
        if (story == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(story);
    }

    @PostMapping("/")
    public ResponseEntity<Story> createStory(@RequestBody Story story) {
        Story savedStory = storyService.saveStory(story);
        return ResponseEntity.ok(savedStory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Story> updateStory(@PathVariable Long id, @RequestBody Story story) {
        Story updatedStory = storyService.updateStory(id, story);
        return ResponseEntity.ok(updatedStory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {
        Story story = storyService.getStoryById(id);
        storyService.deleteStory(story);
        return ResponseEntity.noContent().build();
    }

}
