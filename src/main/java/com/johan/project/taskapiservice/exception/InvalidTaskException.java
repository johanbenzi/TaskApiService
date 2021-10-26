package com.johan.project.taskapiservice.exception;

public class InvalidTaskException extends RuntimeException {
    public InvalidTaskException(final String message) {
        super(message);
    }
}