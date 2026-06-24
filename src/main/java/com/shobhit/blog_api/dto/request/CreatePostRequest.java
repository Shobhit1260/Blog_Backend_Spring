package com.shobhit.blog_api.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CreatePostRequest {
    private String title;
    private String content;
    private List<Long> tagIds=new ArrayList<>();;
}
