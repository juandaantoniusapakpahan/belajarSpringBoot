package com.example.springrestapi.repository;

import com.example.springrestapi.model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {

    @Query("UPDATE Account a SET a.balance = a.balance + ?1 WHERE a.id =?2")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public void deposit(double amount, Long id);

    @Query("UPDATE Account a SET a.balance= a.balance - ?1 WHERE a.id = ?2")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public void withdraw(double amount, Long id);

    public boolean existsById(Long id);


}
