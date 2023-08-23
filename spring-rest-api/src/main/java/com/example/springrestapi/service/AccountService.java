package com.example.springrestapi.service;

import com.example.springrestapi.model.Account;

import java.util.Optional;

public interface AccountService {
    public Account create(Account account);
    public Iterable<Account> findAll();
    public Account findById(Long id);
    public Account deposit(double amount, Long id);

    public Account withdrawal(double amount, Long id);

    public void delete(Long id);
}
