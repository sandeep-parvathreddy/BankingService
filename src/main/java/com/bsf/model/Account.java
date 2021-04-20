package com.bsf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by sandeepreddy on 19/04/21.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Account {

    @Id
    private Long id;

    private double balance;

    @ManyToOne
    private AccountHolder accountHolder;

}
