package com.bsf.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sandeepreddy on 19/04/21.
 */
@Data
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Account> accounts;
}
