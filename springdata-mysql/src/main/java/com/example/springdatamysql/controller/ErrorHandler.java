package com.example.springdatamysql.controller;

import com.example.springdatamysql.Exception.AuthorizeError;
import com.example.springdatamysql.Exception.NotFoundError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    public ErrorHandler() {
        super();
    }

    @ExceptionHandler({NotFoundError.class})
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({AuthorizeError.class})
    protected ResponseEntity<Object> authorizeError(
            Exception ex, WebRequest request
    ){
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
