package com.example.springrestapi.controller;


import com.example.springrestapi.model.Account;
import com.example.springrestapi.model.AccountBalanceRequest;
import com.example.springrestapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/accounts")
public class AccountController {

    @Autowired private AccountService accountService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Account account){
        Account _result = accountService.create(account);
        return new ResponseEntity<>(_result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        Iterable<Account> _result = accountService.findAll();
        return new ResponseEntity<>(_result, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            Account _result = accountService.findById(id);
            return new ResponseEntity<>(_result, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("account not found", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/{id}/deposits")
    public ResponseEntity<?> deposit(@RequestBody AccountBalanceRequest accountBalanceRequest, @PathVariable Long id){
        try{
            Account _result = accountService.deposit(accountBalanceRequest.getAmount(), id);
            return new ResponseEntity<>(_result, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("account not found", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/{id}/withdrawal")
    public ResponseEntity<?> withdrawal(@RequestBody AccountBalanceRequest accountBalanceRequest, @PathVariable Long id){
        try{
            Account _result = accountService.withdrawal(accountBalanceRequest.getAmount(), id);
            return new ResponseEntity<>(_result, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("account not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            accountService.delete(id);
            return new ResponseEntity<>("successfully deleted account", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("account not found", HttpStatus.NOT_FOUND);
        }
    }
}
