package com.bsf.model;

import com.bsf.utils.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sandeepreddy on 19/04/21.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountDTO {

    private Long id;

    private double balance;

    private AccountHolder accountHolder;

    private AccountType type;

}
