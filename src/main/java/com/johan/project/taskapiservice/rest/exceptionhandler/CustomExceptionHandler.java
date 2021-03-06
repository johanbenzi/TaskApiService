package com.johan.project.taskapiservice.rest.exceptionhandler;

import com.johan.project.taskapiservice.exception.InvalidTaskException;
import com.johan.project.taskapiservice.exception.InvalidUserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handle(final IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(final InvalidUserException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(final InvalidTaskException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(final Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>("Unexpected error in performing operation", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
