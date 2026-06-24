package com.shobhit.blog_api.exception;

public class TagNotFoundException extends BlogApplicationException {
    public TagNotFoundException(String message) {
        super(message);
    }
}