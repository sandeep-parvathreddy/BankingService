package com.bsf.model;

import com.bsf.utils.TransactionType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sandeepreddy on 20/04/21.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DebitTransaction.class, name = "debit"),
        @JsonSubTypes.Type(value = CreditTransaction.class, name = "credit"),
        @JsonSubTypes.Type(value = AccountTransferTransaction.class, name = "transfer")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Transaction {

    private Long id;

    private TransactionType type;
}
