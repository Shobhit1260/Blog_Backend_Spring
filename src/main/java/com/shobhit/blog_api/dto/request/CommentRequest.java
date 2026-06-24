package com.shobhit.blog_api.dto.request;

import com.shobhit.blog_api.entity.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommentRequest {
    @NotNull
    private String comment;
}
