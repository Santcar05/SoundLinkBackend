package com.project.soundlink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.soundlink.Entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
