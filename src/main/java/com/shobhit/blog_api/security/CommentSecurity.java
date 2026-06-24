package com.shobhit.blog_api.security;

import com.shobhit.blog_api.entity.Comment;
import com.shobhit.blog_api.entity.Role;
import com.shobhit.blog_api.entity.User;
import com.shobhit.blog_api.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("commentSecurity")
@RequiredArgsConstructor
public class CommentSecurity {

    private final CommentRepository commentRepository;

    public boolean isOwnerOrAdmin(Long commentId) {
        User currentUser =
                (User)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();

        return comment.getAuthor().getId()==(currentUser.getId())
                || currentUser.getRole() == Role.ADMIN;
    }
}