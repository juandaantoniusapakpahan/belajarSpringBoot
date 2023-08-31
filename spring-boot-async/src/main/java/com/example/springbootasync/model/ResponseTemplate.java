package com.example.springbootasync.model;


import lombok.Data;

@Data
public class ResponseTemplate<T> {
    private Integer statusCode;
    private T message;

    public ResponseTemplate(Integer statusCode, T message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
