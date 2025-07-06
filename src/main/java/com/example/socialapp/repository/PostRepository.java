package com.example.socialapp.repository;

import com.example.socialapp.model.Post;
import com.example.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /** Fetch all posts by a given author, newest first */
    List<Post> findAllByAuthorOrderByTimestampDesc(User author);
}
