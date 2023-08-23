package com.example.springrestapi.exception;

public class NoSuchAccountExistsException extends RuntimeException{
    private String message;

    public NoSuchAccountExistsException(){
        super();
    }

    public NoSuchAccountExistsException(String message, Throwable cause){
        super(message,cause);
        this.message = message;
    }

    public NoSuchAccountExistsException(String message){
        super(message);
        this.message = message;
    }

    public NoSuchAccountExistsException(Throwable cause){
        super(cause);
    }
}
