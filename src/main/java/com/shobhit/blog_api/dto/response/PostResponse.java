package com.shobhit.blog_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String authorEmail;
    private List<String> tags;
    private LocalDateTime createdAt;
}