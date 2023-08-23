package com.example.springbootjdbc.controller;

import com.example.springbootjdbc.model.ErrorResponseTemp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleAll(final  Exception ex, final WebRequest request){
        log.error("Exception",ex);
        ErrorResponseTemp errorResponseTemp = new ErrorResponseTemp(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
        return handleExceptionInternal(ex, errorResponseTemp, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
