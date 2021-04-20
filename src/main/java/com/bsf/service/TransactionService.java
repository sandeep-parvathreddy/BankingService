package com.bsf.service;

import com.bsf.model.AccountTransferTransaction;
import com.bsf.model.CreditTransaction;
import com.bsf.model.DebitTransaction;
import com.bsf.model.response.TransactionResponseDTO;

/**
 * Created by sandeepreddy on 19/04/21.
 */
public interface TransactionService {

    TransactionResponseDTO credit(CreditTransaction transaction);

    TransactionResponseDTO debit(DebitTransaction transaction);

    TransactionResponseDTO transfer(AccountTransferTransaction transferTransaction);
}
