package com.example.springbootjdbc.controller;


import com.example.springbootjdbc.model.Account;
import com.example.springbootjdbc.model.Amount;
import com.example.springbootjdbc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired private AccountService accountService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Account account){
        Account _result = accountService.save(account);
        return new ResponseEntity<>(_result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Account> _result = accountService.findAll();
        return new ResponseEntity<>(_result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Account _result = accountService.findById(id);
        return new ResponseEntity<>(_result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        int _id = accountService.deleteById(id);
        return new ResponseEntity<>("successfully delete account " + _id, HttpStatus.OK);
    }

    @PatchMapping("/{id}/deposits")
    public ResponseEntity<?> deposit(@PathVariable Long id, @RequestBody Amount amount){
        Account account = accountService.deposit(id, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PatchMapping("/{id}/withdrawal")
    public ResponseEntity<?> withdrawal(@PathVariable Long id, @RequestBody Amount amount){
        Account account = accountService.withdrawal(id, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
