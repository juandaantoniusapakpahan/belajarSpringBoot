package com.example.springrestapi.controller;

import com.example.springrestapi.exception.AccountAlreadyExistsException;
import com.example.springrestapi.exception.NoSuchAccountExistsException;
import com.example.springrestapi.exception.UserAlreadyExistsException;
import com.example.springrestapi.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error("handle MethodArgumentNotValidException", ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors.toString());
        return createResponseEntity(errorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleAll(final  Exception ex, final WebRequest request){
        log.error("handle Exception",ex);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return createResponseEntity(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,request);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    protected ResponseEntity<Object> handleUserAlreadyExistsException(final Exception ex, final WebRequest request){
        log.error("UserAlreadyExistsException", ex);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
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
