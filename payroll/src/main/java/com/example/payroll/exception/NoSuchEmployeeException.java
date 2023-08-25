package com.example.payroll.exception;

public class NoSuchEmployeeException extends RuntimeException{
    private String message;

    public NoSuchEmployeeException(final String message, final Throwable cause){
        super(message, cause);
        this.message = message;
    }

    public NoSuchEmployeeException(final String message){
        super(message);
        this.message = message;
    }

    public NoSuchEmployeeException(final Throwable cause){
        super(cause);
    }
}
