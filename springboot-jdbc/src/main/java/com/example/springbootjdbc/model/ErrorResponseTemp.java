package com.example.springbootjdbc.model;


import lombok.Data;

@Data
public class ErrorResponseTemp {

    private int statusCode;
    private String message;

    public ErrorResponseTemp(int statusCode, String message){
        this.statusCode =statusCode;
        this.message = message;
    }
}
