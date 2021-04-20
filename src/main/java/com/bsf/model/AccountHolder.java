package com.bsf.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by sandeepreddy on 19/04/21.
 */
@Entity
@Data
public class AccountHolder {

    @Id
    private int id;

}
