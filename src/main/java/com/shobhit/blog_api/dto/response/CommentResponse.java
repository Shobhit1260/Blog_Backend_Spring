package com.shobhit.blog_api.dto.response;

import com.shobhit.blog_api.entity.Post;
import com.shobhit.blog_api.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponse {
    private Long id;
    private String comment;
    private User author;
    private Long postId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
