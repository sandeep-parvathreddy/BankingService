package com.bsf.service;

import com.bsf.dao.Bank;
import com.bsf.model.AccountDTO;

import java.util.List;

/**
 * Created by sandeepreddy on 19/04/21.
 */
public interface BankInterface {

    Bank save(Bank bank);

    List<AccountDTO> getAllAccounts(Long id);
}
