// src/main/java/com/example/socialapp/service/PostService.java
package com.example.socialapp.service;

import com.example.socialapp.model.Post;
import com.example.socialapp.model.User;

import java.util.List;

public interface PostService {
    /** Retrieve all posts by this author, newest first */
    List<Post> findByAuthor(User author);

    /** Create and persist a new post (associates timestamp & author) */
    Post createPost(User author, String content);
}
