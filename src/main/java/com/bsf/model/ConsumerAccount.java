package com.bsf.model;

import com.bsf.utils.AccountType;

/**
 * Created by sandeepreddy on 19/04/21.
 */
public class ConsumerAccount extends AccountDTO {

    public ConsumerAccount( Long accountNumber, double balance, Person person) {

        super(accountNumber,balance,person, AccountType.CONSUMER);
    }
}
