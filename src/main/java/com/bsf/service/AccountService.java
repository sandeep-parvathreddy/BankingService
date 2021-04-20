package com.bsf.service;

import com.bsf.model.AccountDTO;

/**
 * Created by sandeepreddy on 19/04/21.
 */
public interface AccountService {

    AccountDTO create(AccountDTO account);

    AccountDTO get(Long id);

    AccountDTO credit(Long accountNumber, double amount);

    AccountDTO debit(Long accountNumber, double amount);
}
