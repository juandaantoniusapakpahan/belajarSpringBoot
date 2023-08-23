package com.example.springbootjdbc.repository;

import com.example.springbootjdbc.model.Account;
import com.example.springbootjdbc.model.Amount;

import java.util.List;

public interface AccountRepository {
    public Account save(Account account);
    public Account findById(Long id);
    public List<Account> findAll();
    public int deleteById(Long id);
    public Account deposit(Long id, Double amount);
    public Account withdrawal(Long id, Double amount);

}
