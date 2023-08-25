package com.example.payroll.exception;

public class NikEmployeeExistsException extends RuntimeException{
    private String message;

    public NikEmployeeExistsException(final String message, final Throwable cause){
        super(message, cause);
        this.message=message;
    }

    public NikEmployeeExistsException(final String message){
        super(message);
        this.message = message;
    }

    public NikEmployeeExistsException(final Throwable cause){
        super(cause);
    }
}
