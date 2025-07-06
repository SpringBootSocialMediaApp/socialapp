// src/main/java/com/example/socialapp/service/PostServiceImpl.java
package com.example.socialapp.service;

import com.example.socialapp.model.Post;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;

    @Autowired
    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<Post> findByAuthor(User author) {
        // Assumes PostRepository has: List<Post> findAllByAuthorOrderByTimestampDesc(User author);
        return postRepo.findAllByAuthorOrderByTimestampDesc(author);
    }

    @Override
    public Post createPost(User author, String content) {
        // Build a new Post; timestamp is set automatically via @PrePersist
        Post post = Post.builder()
                .content(content)
                .author(author)
                .build();
        return postRepo.save(post);
    }
}
