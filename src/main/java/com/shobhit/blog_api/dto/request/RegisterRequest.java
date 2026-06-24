package com.shobhit.blog_api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String password;
    private String username;
    private String email;
}
