package com.example.payroll.model.response;

import lombok.*;

@Getter
@Setter
public class OutPutResponseTemp<T> {
    private String status;
    private T data;

    public OutPutResponseTemp(String status, T data) {
        this.status = status;
        this.data = data;
    }


}
