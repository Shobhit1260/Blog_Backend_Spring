package com.shobhit.blog_api.service;

import com.shobhit.blog_api.dto.request.CreatePostRequest;
import com.shobhit.blog_api.dto.response.PostResponse;
import com.shobhit.blog_api.entity.Post;
import com.shobhit.blog_api.entity.Tag;
import com.shobhit.blog_api.entity.User;
import com.shobhit.blog_api.exception.PostNotFoundException;
import com.shobhit.blog_api.exception.TagNotFoundException;
import com.shobhit.blog_api.repository.PostRepository;
import com.shobhit.blog_api.repository.TagRepository;
import com.shobhit.blog_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;


    @Transactional
    @PreAuthorize("isAuthenticated()")
    public PostResponse createPost(CreatePostRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<Tag> tags = request.getTagIds() == null
                ? Set.of()
                : request.getTagIds().stream()
                .map(id -> tagRepository.findById(id)
                        .orElseThrow(() -> new TagNotFoundException("Tag not found: " + id)))
                .collect(Collectors.toSet());

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user)
                .tags(tags)
                .createdAt(LocalDateTime.now())
                .build();

        Post saved = postRepository.save(post);
        return mapToResponse(saved);

    }


    @PreAuthorize("permitAll")
    public Page<PostResponse> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(this::mapToResponse);
    }


    @PreAuthorize("permitAll")
    public PostResponse getPostById(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        return mapToResponse(post);
    }


    @Transactional
    @PreAuthorize("@postSecurity.isOwnerOrAdmin(#id)")
    public PostResponse updatePost(Long id, CreatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
        System.out.print("post"+post);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(postRepository.save(post));
    }


    @Transactional
    @PreAuthorize("@postSecurity.isOwnerOrAdmin(#id)")
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
        System.out.print("post"+post);
        postRepository.delete(post);
    }

    @PreAuthorize("permitAll")
    public Page<PostResponse> searchPosts(String keyword, Pageable pageable) {
        return postRepository.findByTitleContainingIgnoreCase(keyword, pageable)
                .map(this::mapToResponse);
    }

    private PostResponse mapToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorEmail(post.getAuthor().getEmail())
                .tags(post.getTags().stream().map(Tag::getTag).toList())
                .createdAt(post.getCreatedAt())
                .build();
    }
}


