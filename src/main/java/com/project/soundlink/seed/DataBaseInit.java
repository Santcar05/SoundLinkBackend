package com.project.soundlink.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import com.project.soundlink.Entity.Story;
import com.project.soundlink.Service.PostService;
import com.project.soundlink.Service.StoryService;
import com.project.soundlink.Service.UserService;

import jakarta.transaction.Transactional;

@Controller
@Transactional
@Profile("default")
public class DataBaseInit implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private StoryService storyService;
    @Autowired
    private PostService postService;

    @Override
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
        init();
    }

    public void init() {
        // Initialize default users
        userService.saveUser(new com.project.soundlink.Entity.User("user1", "user1@example.com", "pass1", 25));
        userService.saveUser(new com.project.soundlink.Entity.User("user2", "user2@example.com", "pass2", 30));
        userService.saveUser(new com.project.soundlink.Entity.User("user3", "user3@example.com", "pass3", 22));
        userService.saveUser(new com.project.soundlink.Entity.User("user4", "user4@example.com", "pass4", 28));
        userService.saveUser(new com.project.soundlink.Entity.User("user5", "user5@example.com", "pass5", 35));

        // Creation of 5 default stories can be added here similarly using StoryService and builder pattern
        //Checkout the isSeen attribute 
        storyService.saveStory(Story.builder().active(true).isSeen(false).timestamp(System.currentTimeMillis()).duration(10000L).user(userService.getUserById(1L)).build());
        storyService.saveStory(Story.builder().active(true).isSeen(false).timestamp(System.currentTimeMillis()).duration(15000L).user(userService.getUserById(2L)).build());
        storyService.saveStory(Story.builder().active(true).isSeen(false).timestamp(System.currentTimeMillis()).duration(20000L).user(userService.getUserById(3L)).build());
        storyService.saveStory(Story.builder().active(true).isSeen(false).timestamp(System.currentTimeMillis()).duration(25000L).user(userService.getUserById(4L)).build());
        storyService.saveStory(Story.builder().active(true).isSeen(false).timestamp(System.currentTimeMillis()).duration(30000L).user(userService.getUserById(5L)).build());


        // Creation of 15 posts using builder pattern
        for (int i = 1; i <= 15; i++) {
            com.project.soundlink.Entity.Post post = com.project.soundlink.Entity.Post.builder()
                    .title("Post Title " + i)
                    .description("This is the description for post " + i)
                    .tags(java.util.Arrays.asList("tag1", "tag2", "tag3"))
                    .likes((int) (Math.random() * 100))
                    .comments((int) (Math.random() * 50))
                    .shares((int) (Math.random() * 20))
                    .timestamp(System.currentTimeMillis())
                    .user(userService.getUserById((long) ((i - 1) % 5 + 1))) // Assigning posts to users in a round-robin fashion
                    .build();
            // Assuming there's a PostService to handle post saving
            postService.savePost(post);
        }

    }
}
