package com.bsf.service;

import com.bsf.model.*;
import com.bsf.model.response.TransactionResponseDTO;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by sandeepreddy on 20/04/21.
 */
@Service
public class DefaultTransactionService implements TransactionService {

    @Autowired
    AccountService accountService;

    @Override
    public TransactionResponseDTO credit(CreditTransaction creditTransaction) {
        setTransactionId(creditTransaction);
        accountService.credit(creditTransaction.getAccountId(),creditTransaction.getAmount());
        return buildTransactionResponse(creditTransaction.getId());
    }

    private String setTransactionId(Transaction transaction) {
        String transactionId = generateTransactionId();
        transaction.setId(transactionId);
        return transactionId;
    }

    private TransactionResponseDTO buildTransactionResponse(String transactionId) {
        return TransactionResponseDTO.builder().transactionId(transactionId).build();
    }

    @Override
    public TransactionResponseDTO debit(DebitTransaction debitTransaction) {
        setTransactionId(debitTransaction);
        accountService.credit(debitTransaction.getAccountId(),debitTransaction.getAmount());
        return buildTransactionResponse(debitTransaction.getId());
    }

    @Override
    public TransactionResponseDTO transfer(AccountTransferTransaction transferTransaction) {
        setTransactionId(transferTransaction);
        AccountDTO fromAccountTx = accountService.debit(transferTransaction.getFromAccountNum(),transferTransaction.getAmount());
        AccountDTO toAccountTx= accountService.credit(transferTransaction.getToAccountNum(),transferTransaction.getAmount());
        return buildTransferTransactionResponse(fromAccountTx,toAccountTx,transferTransaction.getId());
    }

    private TransactionResponseDTO buildTransferTransactionResponse(AccountDTO fromAccountTx, AccountDTO toAccountTx, String id) {
        return TransactionResponseDTO.builder().accounts(List.of(fromAccountTx,toAccountTx)).transactionId(id).build();
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
