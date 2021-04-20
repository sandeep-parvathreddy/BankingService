package com.bsf.mapper;

import com.bsf.dao.Account;
import com.bsf.model.AccountDTO;

public class AccountMapper {

    public static Account toAccount(AccountDTO accountDTO){
        return Account.builder().id(accountDTO.getId()).balance(accountDTO.getBalance()).build();
    }

    public static AccountDTO toAccountDTO(Account account){
        return AccountDTO.builder().id(account.getId()).balance(account.getBalance()).build();
    }
}
