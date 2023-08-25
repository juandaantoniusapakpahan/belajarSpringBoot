package com.example.payroll.exception;

public class NoSuchSalaryMatrixException extends RuntimeException{

    private String message;

    public NoSuchSalaryMatrixException(String message, Throwable cause){
        super(message, cause);
        this.message = message;
    }

    public NoSuchSalaryMatrixException(String message){
        super(message);
        this.message = message;
    }
    public NoSuchSalaryMatrixException(Throwable cause){
        super(cause);
    }
}
