package com.example.springbootjdbc.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Amount {
    private Double amount;

    public Amount(Double amount){
        this.amount = amount;
    }
}
