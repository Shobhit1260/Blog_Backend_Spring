package com.shobhit.blog_api.security;

import com.shobhit.blog_api.entity.Post;
import com.shobhit.blog_api.entity.Role;
import com.shobhit.blog_api.entity.User;
import com.shobhit.blog_api.repository.PostRepository;
import com.shobhit.blog_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("postSecurity")
@RequiredArgsConstructor
public class PostSecurity {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public boolean isOwnerOrAdmin(Long postId) {

        User user = (User)  SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Post post = postRepository.findById(postId)
                .orElseThrow();

        boolean isOwner = post.getAuthor().getId()==user.getId();
        boolean isAdmin = user.getRole() == Role.ADMIN;

        return isOwner || isAdmin;
    }
}