package com.bsf.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by sandeepreddy on 20/04/21.
 */
@Builder
@Data
public class AccountTransferTransaction extends Transaction{

    private Long fromAccountNum;

    private Long toAccountNum;

    private double amount;
}
