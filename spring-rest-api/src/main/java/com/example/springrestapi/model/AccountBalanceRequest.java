package com.example.springrestapi.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountBalanceRequest {
    private Double amount;

    public AccountBalanceRequest(Double amount){
        this.amount = amount;
    }
}
