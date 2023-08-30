package com.example.springrestapi.service;

import com.example.springrestapi.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public Account create(Account account);
    public List<Account> findAll();
    public Account findById(Long id);
    public Account deposit(double amount, Long id);

    public Account withdrawal(double amount, Long id);

    public void delete(Long id);
}
