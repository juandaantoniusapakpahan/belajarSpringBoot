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

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler({UserAlreadyExistsException.class})
    protected ResponseEntity<Object> handleUserAlreadyExistsException(final Exception ex, final WebRequest request){
        log.error("UserAlreadyExistsException", ex);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
