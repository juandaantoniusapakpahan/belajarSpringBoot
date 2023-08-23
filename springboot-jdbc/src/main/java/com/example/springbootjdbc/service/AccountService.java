package com.example.springbootjdbc.service;


import com.example.springbootjdbc.model.Account;
import com.example.springbootjdbc.model.Amount;

import java.util.List;

public interface AccountService {
    public Account save(Account account);
    public List<Account> findAll();
    public Account findById(Long id);
    public int deleteById(Long id);
    public Account deposit(Long id, Amount amount);
    public Account withdrawal(Long id, Amount amount);
}
