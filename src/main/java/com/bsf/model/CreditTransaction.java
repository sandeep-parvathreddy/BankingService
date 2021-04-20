package com.bsf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sandeepreddy on 20/04/21.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreditTransaction extends Transaction{

    private Long accountId;

    private Double amount;
}
