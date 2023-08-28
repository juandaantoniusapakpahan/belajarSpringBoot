package com.example.payroll.exception;

import com.example.payroll.model.response.ErrorResponseTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                errors.toString()
        );
        return handleExceptionInternal(ex, errorResponseTemplate, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({
            GradeSalaryMatrixExistsException.class,
            NikEmployeeExistsException.class,
            PeriodPayrollExistsException.class
    })
    protected ResponseEntity<Object> handleGradeSalaryMatrixExistsException(final Exception ex, final WebRequest request){
        log.error("handle GradeSalaryMatrixExistsException",ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
        return handleExceptionInternal(ex, errorResponseTemplate, headers, HttpStatus.CONFLICT,request);
    }

    @ExceptionHandler({
            NoSuchSalaryMatrixException.class,
            NoSuchEmployeeException.class,
            NoSuchPayrollException.class
    })
    protected ResponseEntity<Object> handleNoSuchSalaryMatrixException(final Exception ex, final WebRequest request){
        log.error("handle NoSuchSalaryMatrixException",ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return handleExceptionInternal(ex, errorResponseTemplate, headers, HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handlerException(final Exception ex, final WebRequest request){
        log.error("handle Exception",ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return handleExceptionInternal(
                ex,
                errorResponseTemplate,
                headers,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }
}
