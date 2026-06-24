package com.shobhit.blog_api.exception;

public class BlogApplicationException extends RuntimeException {
    public BlogApplicationException(String message) {
        super(message);
    }
}