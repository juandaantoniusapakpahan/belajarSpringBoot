package com.example.oauthtwologin.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationError extends RuntimeException{

    public AuthenticationError(){
        super();
    }

    public AuthenticationError(final String message, final Throwable cause){
        super(message, cause);
    }

    public AuthenticationError(final String message){
        super(message);
    }

    public  AuthenticationError(final Throwable cause){
        super(cause);
    }
}
