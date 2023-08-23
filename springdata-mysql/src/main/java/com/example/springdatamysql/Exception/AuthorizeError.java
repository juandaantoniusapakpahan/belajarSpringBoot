package com.example.springdatamysql.Exception;

public class AuthorizeError extends RuntimeException{
    public AuthorizeError(){
        super();
    }

    public AuthorizeError(final String message, final Throwable cause){
        super(message, cause);
    }

    public AuthorizeError(final String message){
        super(message);
    }

    public AuthorizeError( final Throwable cause){
        super(cause);
    }
}
