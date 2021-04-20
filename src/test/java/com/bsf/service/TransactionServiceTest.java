package com.bsf.service;

import com.bsf.model.AccountDTO;
import com.bsf.model.AccountTransferTransaction;
import com.bsf.model.response.TransactionResponseDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceTest {

    @Mock
    AccountService accountService;

    @InjectMocks
    DefaultTransactionService transactionService;

    @Test
    public void do_transfer(){
        AccountTransferTransaction accountTransferTransaction = AccountTransferTransaction.builder().fromAccountNum(123L).toAccountNum(456L).amount(10).build();
        Mockito.when(accountService.debit(123L,10)).thenReturn(AccountDTO.builder().id(123L).balance(90).build());
        Mockito.when(accountService.credit(456L,10)).thenReturn(AccountDTO.builder().id(345L).balance(110).build());

        TransactionResponseDTO responseDTO = transactionService.transfer(accountTransferTransaction);
        Assert.assertEquals(90,responseDTO.getAccounts().get(0).getBalance(),0.0);
        Assert.assertEquals(110,responseDTO.getAccounts().get(1).getBalance(),0.0);

    }

    @Test(expected = RuntimeException.class)
    public void do_insuffcient_transfer(){
        AccountTransferTransaction accountTransferTransaction = AccountTransferTransaction.builder().fromAccountNum(123L).toAccountNum(456L).amount(10).build();
        Mockito.when(accountService.debit(123L,10)).thenThrow(new RuntimeException());
        transactionService.transfer(accountTransferTransaction);

    }
}
