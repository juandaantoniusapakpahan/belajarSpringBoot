package com.example.payroll.exception;

import com.example.payroll.helper.ErrorResponseTemplate;
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
public class GlobalErrorHandle extends ResponseEntityExceptionHandler {
    Logger log = LoggerFactory.getLogger(GlobalErrorHandle.class);
//    int status,
//    String error,
//    String message
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handlerException(final Exception ex, final WebRequest request){
        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage()
                );
        return handleExceptionInternal(
                ex,
                errorResponseTemplate,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }
}
