package com.example.payroll.exception;

import com.example.payroll.helper.ErrorResponseTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class SalaryMatrixErrorHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler({GradeSalaryMatrixExistsException.class})
    protected ResponseEntity<Object> handleGradeSalaryMatrixExistsException(final Exception ex, final WebRequest request){
        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                ex.getMessage()
        );
        return handleExceptionInternal(ex, errorResponseTemplate, new HttpHeaders(), HttpStatus.CONFLICT,request);
    }

    @ExceptionHandler({NoSuchSalaryMatrixException.class})
    protected ResponseEntity<Object> handleNoSuchSalaryMatrixException(final Exception ex, final WebRequest request){
        ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage()
        );
        return handleExceptionInternal(ex, errorResponseTemplate, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
