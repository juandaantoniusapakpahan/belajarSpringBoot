package com.example.springrestapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String accountNumber;
    private Double balance;

    public Account(String accountNumber, Double balance){
        this.accountNumber =accountNumber;
        this.balance = balance;
    }
}
