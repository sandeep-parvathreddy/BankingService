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
        @JsonSubTypes.Type(value = DebitTransaction.class, name = "DEBIT"),
        @JsonSubTypes.Type(value = CreditTransaction.class, name = "CREDIT"),
        @JsonSubTypes.Type(value = AccountTransferTransaction.class, name = "TRANSFER")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Transaction {

    private String id;

    private TransactionType type;
}
