package com.example.payroll.exception;

import com.example.payroll.helper.ErrorResponseTemplate;
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


@ControllerAdvice
public class GlobalErrorHandle extends ResponseEntityExceptionHandler {
    Logger log = LoggerFactory.getLogger(GlobalErrorHandle.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {
        log.error("handler MethodArgumentNotValidException",ex);

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponseTemplate errorResponseTemplate  = new ErrorResponseTemplate(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                errors.toString()
        );
        return handleExceptionInternal(ex, errorResponseTemplate, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({
            GradeSalaryMatrixExistsException.class,
            NikEmployeeExistsException.class})
    protected ResponseEntity<Object> handleGradeSalaryMatrixExistsException(final Exception ex, final WebRequest request){
        log.error("handle GradeSalaryMatrixExistsException",ex);
        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                ex.getMessage()
        );
        return handleExceptionInternal(ex, errorResponseTemplate, new HttpHeaders(), HttpStatus.CONFLICT,request);
    }

    @ExceptionHandler({NoSuchSalaryMatrixException.class})
    protected ResponseEntity<Object> handleNoSuchSalaryMatrixException(final Exception ex, final WebRequest request){
        log.error("handle NoSuchSalaryMatrixException",ex);
        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage()
        );
        return handleExceptionInternal(ex, errorResponseTemplate, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handlerException(final Exception ex, final WebRequest request){
        log.error("handle Exception",ex);
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
