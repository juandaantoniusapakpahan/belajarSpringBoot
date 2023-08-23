package com.example.springdatamysql.Exception;

public class NotFoundError extends RuntimeException
{
    public NotFoundError(){
        super();
    }

    public NotFoundError(final String message, final Throwable cause){
        super(message, cause);
    }
    public NotFoundError(final String message){
        super(message);
    }

    public NotFoundError(final Throwable cause) {
        super(cause);
    }
}
