package com.example.springrestapi.exception;

public class UserAlreadyExistsException extends RuntimeException{

    private String message;

    public UserAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
        this.message =message;
    }

    public UserAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }

    public UserAlreadyExistsException(Throwable cause){
        super(cause);
    }
}
