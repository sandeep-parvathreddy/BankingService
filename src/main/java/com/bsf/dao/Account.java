package com.bsf.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Account {
    @Id
    private Long id;

    private double balance;

    @ManyToOne
    private AccountHolder accountHolder;
}
