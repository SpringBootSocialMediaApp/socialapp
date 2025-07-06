package com.example.socialapp.controller;

import com.example.socialapp.model.Post;
import com.example.socialapp.model.User;
import com.example.socialapp.service.PostService;
import com.example.socialapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService,
                          UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    /**
     * Render the home feed: shows current user's posts and the post form.
     */
    @GetMapping({"/", "/home"})
    public String homeFeed(
            @AuthenticationPrincipal UserDetails currentUser,
            Model model
    ) {
        User user = userService.findByEmail(currentUser.getUsername());
        List<Post> posts = postService.findByAuthor(user);

        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "home";
    }

    /**
     * Handle new post submissions.
     */
    @PostMapping("/posts")
    public String createPost(
            @ModelAttribute("post") Post post,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        User user = userService.findByEmail(currentUser.getUsername());
        postService.createPost(user, post.getContent());
        return "redirect:/home";
    }
}
