package com.shobhit.blog_api.service;

import com.shobhit.blog_api.dto.request.CommentRequest;
import com.shobhit.blog_api.dto.response.CommentResponse;
import com.shobhit.blog_api.entity.Comment;
import com.shobhit.blog_api.entity.Post;
import com.shobhit.blog_api.entity.User;
import com.shobhit.blog_api.repository.CommentRepository;
import com.shobhit.blog_api.repository.PostRepository;
import com.shobhit.blog_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentResponse createComment(Long postId, CommentRequest commentRequest,User user){
        Post post = postRepository.findById(postId)
                .orElseThrow();
        Comment comment= Comment.builder()
                .content(commentRequest.getComment())
                .post(post)
                .author(user)
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);

        return mapToResponse(comment);

    }
    public List<CommentResponse> getCommentsByPost(Long postId) {
        return commentRepository.findByPost_Id(postId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @PreAuthorize("@commentSecurity.isOwnerOrAdmin(#commentId)")
    public CommentResponse updateComment(Long commentId,
                                         CommentRequest request) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();
        comment.setContent(request.getComment());
        commentRepository.save(comment);
        return mapToResponse(comment);
    }


    @PreAuthorize("@commentSecurity.isOwnerOrAdmin(#commentId)")
    public void deleteComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();

        commentRepository.delete(comment);
    }


    private CommentResponse mapToResponse(Comment comment) {

        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getContent())
                .author(comment.getAuthor())
                .postId(comment.getPost().getId())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
