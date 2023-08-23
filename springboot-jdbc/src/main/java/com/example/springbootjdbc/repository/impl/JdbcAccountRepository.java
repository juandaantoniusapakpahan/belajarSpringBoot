package com.example.springbootjdbc.repository.impl;

import com.example.springbootjdbc.model.Account;
import com.example.springbootjdbc.model.Amount;
import com.example.springbootjdbc.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JdbcAccountRepository implements AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account save(Account account){
        return jdbcTemplate.queryForObject("INSERT INTO accounts (account_number, balance) VALUES(?,?) RETURNING *", BeanPropertyRowMapper.newInstance(Account.class),account.getAccountNumber(),account.getBalance());
    }

    @Override
    public Account findById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE id = ?", BeanPropertyRowMapper.newInstance(Account.class), id);
    }

    @Override
    public List<Account> findAll(){
        return jdbcTemplate.query("SELECT * FROM accounts", BeanPropertyRowMapper.newInstance(Account.class));
    }

    @Override
    public int deleteById(Long id){
        Account account = jdbcTemplate.queryForObject("DELETE FROM accounts WHERE id = ? RETURNING *",BeanPropertyRowMapper.newInstance(Account.class), id);
        return account.getId();
    }

    @Override
    public Account deposit(Long id, Double amount){
        return jdbcTemplate.queryForObject("UPDATE accounts SET balance = balance + ? WHERE id = ? RETURNING *", BeanPropertyRowMapper.newInstance(Account.class), amount, id);
    }

    @Override
    public Account withdrawal(Long id, Double amount){
        return jdbcTemplate.queryForObject("UPDATE accounts SET balance = balance - ? WHERE id = ? RETURNING *", BeanPropertyRowMapper.newInstance(Account.class), amount, id);
    }
}
