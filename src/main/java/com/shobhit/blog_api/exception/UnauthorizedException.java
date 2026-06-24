package com.shobhit.blog_api.exception;

public class UnauthorizedException extends BlogApplicationException {
    public UnauthorizedException(String message) {
        super(message);
    }
}