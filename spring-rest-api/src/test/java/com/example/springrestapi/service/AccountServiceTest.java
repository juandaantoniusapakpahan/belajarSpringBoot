package com.example.springrestapi.service;


import com.example.springrestapi.exception.NoSuchAccountExistsException;
import com.example.springrestapi.model.Account;
import com.example.springrestapi.repository.AccountRepository;
import com.example.springrestapi.service.impl.AccountServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService = new AccountServiceImpl();

    @BeforeEach
    void setMockOutPut(){
        List<Account> accountList =new ArrayList<Account>();

        accountList.add(new Account("123",10000.0));
        accountList.add(new Account("124",10000.0));
        accountList.add(new Account("125",10000.0));
        accountList.add(new Account("126",10000.0));
        accountList.add(new Account("127",10000.0));
        accountList.add(new Account("128",10000.0));

        lenient().when(accountRepository.findAll()).thenReturn(accountList);
        lenient().when(accountRepository.save(any())).thenReturn(new Account("128",100000.0));
        lenient().when(accountRepository.existsById(any())).thenReturn(Boolean.TRUE);
        lenient().when(accountRepository.findById(any())).thenReturn(Optional.of(new Account("128", 100000.0)));
    }

    @Test
    void testFindAll(){
        List<Account> accountList = accountService.findAll();
        assertEquals(6,accountList.size());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void testFindById(){
        lenient().when(accountRepository.findById(any())).thenReturn(Optional.of(new Account("128", 100000.0)));
        Account account = accountService.findById((long)10);
        assertEquals(account.getAccountNumber(),"128");
    }

    @Test
    void testCreate(){
        lenient().when(accountRepository.findById(any())).thenReturn(Optional.of(new Account("128", 100000.0)));
        Account account = accountService.create(any());
        assertEquals(account.getAccountNumber(),"128");
        assertEquals(account.getBalance(), 100000.0);
    }

    @Test
    void testWithDraw(){
        lenient().when(accountRepository.findById(any())).thenReturn(Optional.of(new Account("128", 100000.0)));
        Account account = accountService.withdrawal(100000.0, any());
        assertEquals(account.getAccountNumber(), "128");
        assertEquals(account.getBalance(), 100000.0);
    }

    @Test
    void testDeposit(){
        lenient().when(accountRepository.findById(any())).thenReturn(Optional.of(new Account("128", 100000.0)));
        Account account = accountService.deposit(100000.0, any());
        assertEquals(account.getAccountNumber(),"128");
    }

    @Test
    void testFindByIdNotFound(){
        lenient().when(accountService.findById(any())).thenThrow(new NoSuchAccountExistsException("no such account exists"));
        assertThrows(NoSuchAccountExistsException.class,()->{
            accountService.findById(any());
        });

    }

    @Test
    void testDepositNotFound(){
        lenient().when(accountService.deposit(100000.0, any())).thenThrow(new NoSuchAccountExistsException("no such account exists"));

        assertThrows(NoSuchAccountExistsException.class, ()->{
           accountService.deposit(10000.0, any());
        });
    }

    @Test
    void testWithdrawNotFound(){
        lenient().when(accountService.withdrawal(10000.0, any())).thenThrow(new NoSuchAccountExistsException("no such account exists"));
        assertThrows(NoSuchAccountExistsException.class, ()->{
            accountService.withdrawal(1000.0, any());
        });
    }

}
