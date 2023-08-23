package com.example.springrestapi.exception;


public class AccountAlreadyExistsException extends RuntimeException{
    private String message;

    public AccountAlreadyExistsException(){
        super();
    }

    public AccountAlreadyExistsException(String message, Throwable cause){
        super(message,cause);
        this.message = message;
    }

    public AccountAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }

    public AccountAlreadyExistsException(Throwable cause){
        super(cause);
    }
}
