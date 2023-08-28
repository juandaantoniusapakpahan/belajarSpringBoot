package com.example.payroll.exception;

public class NoSuchPayrollException extends RuntimeException{
    private String message;

    public NoSuchPayrollException(final String message, final Throwable cause){
        super(message, cause);
        this.message = message;
    }
    public NoSuchPayrollException(final String message){
        super(message);
        this.message = message;
    }
    public NoSuchPayrollException(final Throwable cause){
        super(cause);
    }
}
