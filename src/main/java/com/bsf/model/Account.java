package com.bsf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by sandeepreddy on 19/04/21.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public abstract class Account {

    private Long id;

    private double balance;

    private AccountHolder accountHolder;

}
