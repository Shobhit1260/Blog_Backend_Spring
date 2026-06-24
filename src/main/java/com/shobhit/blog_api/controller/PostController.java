package com.shobhit.blog_api.controller;

import com.shobhit.blog_api.dto.request.CreatePostRequest;
import com.shobhit.blog_api.dto.response.PostResponse;
import com.shobhit.blog_api.entity.User;
import com.shobhit.blog_api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public PostResponse createPost(
            @RequestBody CreatePostRequest request,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return postService.createPost(request, user.getEmail());
    }

    @GetMapping
    public Page<PostResponse> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return postService.getAllPosts(pageable);
    }

    @GetMapping("/{id}")
    public PostResponse getById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/update/{id}")
    public PostResponse updatePost(
            @PathVariable Long id,
            @RequestBody CreatePostRequest request
    ) {
        return postService.updatePost(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(
            @PathVariable Long id

    ) {
        System.out.print("Entered");
        postService.deletePost(id);
    }

    @GetMapping("/search")
    public Page<PostResponse> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return postService.searchPosts(keyword, pageable);
    }
}