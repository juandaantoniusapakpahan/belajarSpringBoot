package com.example.payroll.exception;


public class GradeSalaryMatrixExistsException extends RuntimeException{
    private String message;

    public GradeSalaryMatrixExistsException(String message, Throwable cause){
        super(message, cause);
        this.message =message;
    }

    public GradeSalaryMatrixExistsException(String message){
        super(message);
        this.message =message;
    }

    public GradeSalaryMatrixExistsException(Throwable cause){
        super(cause);
    }
}
