package com.example.springrestapi.service.impl;

import com.example.springrestapi.exception.AccountAlreadyExistsException;
import com.example.springrestapi.exception.NoSuchAccountExistsException;
import com.example.springrestapi.model.Account;
import com.example.springrestapi.repository.AccountRepository;
import com.example.springrestapi.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired private AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        try{
            return accountRepository.save(account);
        }catch (DataIntegrityViolationException e){
            throw new AccountAlreadyExistsException("Account already exists",e);
        }catch (NoSuchElementException e){
            throw new NoSuchAccountExistsException("Account already exists",e);
        }
    }

    @Override
    public Iterable<Account> findAll(){
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id){
       if(accountRepository.existsById(id)){
           return accountRepository.findById(id).get();
       }else{
           throw new NoSuchAccountExistsException("No Such Account Exists");
       }
    }

    @Override
    public Account deposit(double amount, Long id){
        accountRepository.findById(id).get();
        accountRepository.deposit(amount, id);
        return accountRepository.findById(id).get();
    }

    @Override
    public Account withdrawal(double amount, Long id){
        accountRepository.findById(id).get();
        accountRepository.withdraw(amount, id);
        return accountRepository.findById(id).get();
    }

    @Override
    public void delete(Long id){
        accountRepository.findById(id).get();
        accountRepository.deleteById(id);
    }
}
