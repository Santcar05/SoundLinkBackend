package com.project.soundlink.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.soundlink.Entity.Post;
import com.project.soundlink.Entity.User;
import com.project.soundlink.Service.PostService;
import com.project.soundlink.Service.UserService;
import com.project.soundlink.dto.CreatePostRequest;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<Post> getPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping("/")
    public Post createPost(@RequestBody CreatePostRequest post) {
        User user = userService.getUserById(post.userId);
        Post newPost = new Post();
        newPost.setUser(user);
        newPost.setTitle(post.title);
        newPost.setDescription(post.description);
        newPost.setTags(post.tags);
        newPost.setTimestamp(post.timestamp);
        newPost.setLikes(0);
        newPost.setComments(0);
        newPost.setShares(0);

        return postService.savePost(newPost);
    }

    @PutMapping("/")
    public Post updatePost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
