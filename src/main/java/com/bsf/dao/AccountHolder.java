package com.bsf.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by sandeepreddy on 19/04/21.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder {

    @Id
    private int id;

}
