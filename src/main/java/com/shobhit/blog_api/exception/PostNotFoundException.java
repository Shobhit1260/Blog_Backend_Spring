package com.shobhit.blog_api.exception;

public class PostNotFoundException extends BlogApplicationException {
    public PostNotFoundException(String message) {
        super(message);
    }
}