package com.example.springbootjdbc.controller;

import com.example.springbootjdbc.Exception.AccountAlreadyExistsException;
import com.example.springbootjdbc.Exception.NoSuchAccountExistsException;
import com.example.springbootjdbc.model.ErrorResponseTemp;
import jakarta.servlet.http.HttpServletResponse;
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
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(AccountExceptionHandler.class);

    @ExceptionHandler({AccountAlreadyExistsException.class})
    protected ResponseEntity<Object> handlerAccountAlreadyExistsException(final Exception ex, final WebRequest request){
        log.error("AccountAlreadyExistsException",ex);
        ErrorResponseTemp errorResponseTemp = new ErrorResponseTemp(HttpStatus.CONFLICT.value(), ex.getMessage());
        return handleExceptionInternal(ex, errorResponseTemp,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({NoSuchAccountExistsException.class})
    protected ResponseEntity<Object> handleNoSuchAccountExistsException(final Exception ex, final WebRequest request){
        log.error("NoSuchAccountExistsException",ex);
        ErrorResponseTemp errorResponseTemp = new ErrorResponseTemp(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return handleExceptionInternal(ex, errorResponseTemp, new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }

}
