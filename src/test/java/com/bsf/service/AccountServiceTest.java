package com.bsf.service;

import com.bsf.dao.Account;
import com.bsf.mapper.AccountMapper;
import com.bsf.model.AccountDTO;
import com.bsf.repository.AccountRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {

    @InjectMocks
    DefaultAccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    AccountDTO john;

    @Before
    public void setUp(){
        john = AccountDTO.builder().id(123L).balance(100).build();
    }

    @After
    public void tearDown() throws Exception {
        john = null;
    }
    @Test
    public void create_account(){
        Account johnAccount = AccountMapper.toAccount(john);
        Mockito.when(accountRepository.save(johnAccount)).thenReturn(johnAccount);
        AccountDTO response = accountService.create(john);
        Assert.assertEquals(john.getId(),response.getId());
        Assert.assertEquals(john.getBalance(),response.getBalance(),0.0);
    }

    @Test
    public void get_account(){
        Account johnAccount = AccountMapper.toAccount(john);
        Mockito.when(accountRepository.findById(johnAccount.getId())).thenReturn(Optional.of(johnAccount));
        AccountDTO response = accountService.get(john.getId());
        Assert.assertEquals(john.getId(),response.getId());
        Assert.assertEquals(john.getBalance(),response.getBalance(),0.0);
    }

    @Test(expected = EntityNotFoundException.class)
    public void get_invalid_account(){
        Account johnAccount = AccountMapper.toAccount(john);
        Mockito.when(accountRepository.findById(johnAccount.getId())).thenReturn(Optional.empty());
        accountService.get(john.getId());
    }

    @Test
    public void credit_amount(){
        Account johnAccount = AccountMapper.toAccount(john);
        Mockito.when(accountRepository.findById(johnAccount.getId())).thenReturn(Optional.of(johnAccount));
        AccountDTO responseDTO = accountService.credit(john.getId(),10);
        Assert.assertEquals(john.getBalance()+10,responseDTO.getBalance(),0.0);
    }

    @Test
    public void debit_amount(){
        Account johnAccount = AccountMapper.toAccount(john);
        Mockito.when(accountRepository.findById(johnAccount.getId())).thenReturn(Optional.of(johnAccount));
        AccountDTO responseDTO = accountService.debit(john.getId(),10);
        Assert.assertEquals(john.getBalance()-10,responseDTO.getBalance(),0.0);
    }

    @Test(expected = RuntimeException.class)
    public void debit_insufficent_amount(){
        Account johnAccount = AccountMapper.toAccount(john);
        Mockito.when(accountRepository.findById(johnAccount.getId())).thenReturn(Optional.of(johnAccount));
        AccountDTO responseDTO = accountService.debit(john.getId(),john.getBalance()+1);
        Assert.assertEquals(john.getBalance()-10,responseDTO.getBalance(),0.0);
    }
}
