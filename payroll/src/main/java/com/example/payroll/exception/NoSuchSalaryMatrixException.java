package com.example.payroll.exception;

public class NoSuchSalaryMatrixException extends RuntimeException{
    private String message;

    public NoSuchSalaryMatrixException(final String message, final Throwable cause){
        super(message, cause);
        this.message = message;
    }

    public NoSuchSalaryMatrixException(final String message){
        super(message);
        this.message = message;
    }

    public NoSuchSalaryMatrixException(final Throwable cause){
        super(cause);
    }
}
