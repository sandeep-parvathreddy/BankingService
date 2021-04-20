package com.bsf.controller;

import com.bsf.model.AccountDTO;
import com.bsf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sandeepreddy on 20/04/21.
 */
@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO account){
        AccountDTO response = accountService.create(account);
        return new ResponseEntity<AccountDTO>(response, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> get(@PathVariable Long id){
        AccountDTO account = accountService.get(id);
        return new ResponseEntity<AccountDTO>(account,HttpStatus.OK);
    }
    


}
