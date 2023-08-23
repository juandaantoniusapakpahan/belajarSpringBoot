package com.example.springbootjdbc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
    private int id;
    private String accountNumber;
    private Double balance;
    public Account(String accountNumber, Double balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
