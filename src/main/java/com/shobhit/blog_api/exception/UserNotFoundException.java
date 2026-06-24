package com.shobhit.blog_api.exception;

public class UserNotFoundException extends BlogApplicationException {
    public UserNotFoundException(String message) {
        super(message);
    }
}