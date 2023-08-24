package com.example.springrestapi.exception;

import com.example.springrestapi.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ NoSuchAccountExistsException.class, NoSuchElementException.class})
    protected ResponseEntity<Object> handleNoSuchAccountExistsException(final Exception ex, final WebRequest request){
        log.error("handle NoSuchAccountExistsException, NoSuchElementException",ex);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return handleExceptionInternal(ex,errorResponse,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler({AccountAlreadyExistsException.class})
    protected ResponseEntity<Object> handleAccountAlreadyExistsException(final Exception ex, final WebRequest request){
        log.error("handle AccountAlreadyExistsException", ex);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return handleExceptionInternal(ex,errorResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
