package com.bsf.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by sandeepreddy on 19/04/21.
 */
@Data
@AllArgsConstructor
public class Person extends AccountHolder {
    private String firstName;
    private String lastName;
}
