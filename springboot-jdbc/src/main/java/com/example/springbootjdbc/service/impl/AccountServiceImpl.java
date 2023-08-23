package com.example.springbootjdbc.service.impl;

import com.example.springbootjdbc.Exception.AccountAlreadyExistsException;
import com.example.springbootjdbc.Exception.NoSuchAccountExistsException;
import com.example.springbootjdbc.model.Account;
import com.example.springbootjdbc.model.Amount;
import com.example.springbootjdbc.repository.AccountRepository;
import com.example.springbootjdbc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account account){
        try {
            return accountRepository.save(account);
        }catch (DataIntegrityViolationException e){
            throw new AccountAlreadyExistsException("Account already exists");
        }
    }
    @Override
    public List<Account> findAll(){
        return accountRepository.findAll();
    }
    @Override
    public Account findById(Long id){
       try{
         return accountRepository.findById(id);
       }catch (EmptyResultDataAccessException ex){
           throw new NoSuchAccountExistsException("No Such Account Exists");
       }
    }

    @Override
    public int deleteById(Long id){
        try{
            return accountRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new NoSuchAccountExistsException("No Such Account Exists");
        }
    }

    @Override
    public Account deposit(Long id, Amount amount) {
        try{
            return accountRepository.deposit(id, amount.getAmount());
        }catch (DataIntegrityViolationException ex){
            throw new NoSuchAccountExistsException("No Such Account Exists");
        }catch (EmptyResultDataAccessException ex){
            throw new NoSuchAccountExistsException("No Such Account Exists");
        }
    }

    @Override
    public Account withdrawal(Long id, Amount amount) {
        try{
            return accountRepository.withdrawal(id, amount.getAmount());
        }catch (DataIntegrityViolationException ex){
            throw new NoSuchAccountExistsException("No Such Account Exists");
        }catch (EmptyResultDataAccessException ex){
            throw new NoSuchAccountExistsException("No Such Account Exists");
        }
    }
}
