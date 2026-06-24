package com.shobhit.blog_api.controller;

import com.shobhit.blog_api.dto.request.CommentRequest;
import com.shobhit.blog_api.dto.response.CommentResponse;
import com.shobhit.blog_api.entity.User;
import com.shobhit.blog_api.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create/{postId}")
    public CommentResponse createComment(@PathVariable Long postId, @RequestBody CommentRequest commentRequest , Authentication authentication) {
           User user=(User) authentication.getPrincipal();
           return commentService.createComment(postId,commentRequest,user);
    }

    @GetMapping("/{postId}")
    public List<CommentResponse> getComments(
            @PathVariable Long postId) {

        return commentService.getCommentsByPost(postId);
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequest request) {

        return commentService.updateComment(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @PathVariable Long commentId) {

        commentService.deleteComment(commentId);
    }
}
