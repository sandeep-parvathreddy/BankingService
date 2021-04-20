package com.bsf.controller;

import com.bsf.model.AccountTransferTransaction;
import com.bsf.model.response.TransactionResponseDTO;
import com.bsf.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sandeepreddy on 20/04/21.
 */
@RestController
@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> transfer(@RequestBody AccountTransferTransaction accountTransferTransaction){
        TransactionResponseDTO response = transactionService.transfer(accountTransferTransaction);
        return new ResponseEntity<TransactionResponseDTO>(response, HttpStatus.OK);
    }

}
