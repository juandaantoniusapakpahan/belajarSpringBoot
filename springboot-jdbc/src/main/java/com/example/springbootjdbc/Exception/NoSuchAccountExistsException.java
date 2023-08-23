package com.example.springbootjdbc.Exception;

public class NoSuchAccountExistsException extends RuntimeException{

    private String message;

    public NoSuchAccountExistsException(final String message, final Throwable cause){
        super(message, cause);
        this.message = message;
    }

    public NoSuchAccountExistsException(final String message){
        super(message);
        this.message =message;
    }
    public NoSuchAccountExistsException(final Throwable cause){
        super(cause);
    }
}
