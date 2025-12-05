package com.project.soundlink.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.soundlink.Entity.Post;
import com.project.soundlink.Repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Add methods to handle Post operations (CRUD) here
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
