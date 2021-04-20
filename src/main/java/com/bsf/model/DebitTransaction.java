package com.bsf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sandeepreddy on 20/04/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebitTransaction extends Transaction{

    private Long accountId;

    private Double amount;
}
