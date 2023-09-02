package com.example.springbootasync.model;


import lombok.Data;

@Data
public class ResponseTemplate<T> {
    private Integer statusCode;
    private T data;

    public ResponseTemplate(Integer statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
