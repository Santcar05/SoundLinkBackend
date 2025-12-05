package com.project.soundlink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.soundlink.Entity.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

}
