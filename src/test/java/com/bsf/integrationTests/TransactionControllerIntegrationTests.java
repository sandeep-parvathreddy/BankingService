package com.bsf.integrationTests;

import com.bsf.BankingServiceApplication;
import com.bsf.model.AccountDTO;
import com.bsf.model.AccountTransferTransaction;
import com.bsf.model.response.TransactionResponseDTO;
import com.bsf.utils.AccountType;
import com.bsf.utils.TransactionType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = BankingServiceApplication.class)
@AutoConfigureMockMvc
public class TransactionControllerIntegrationTests {

    TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void transfer_transaction(){
        AccountDTO accountDTO = AccountDTO.builder().balance(100.0).type(AccountType.CONSUMER).build();
        ResponseEntity<AccountDTO> account1 = createAccount(accountDTO);
        ResponseEntity<AccountDTO> account2 = createAccount(accountDTO);

        AccountTransferTransaction accountTransferTransaction = AccountTransferTransaction.builder().fromAccountNum(account1.getBody().getId()).toAccountNum(account2.getBody().getId()).amount(10.0).build();
        ResponseEntity<TransactionResponseDTO> responseEntity = doTransaction(accountTransferTransaction);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ResponseEntity<AccountDTO> getAccount1Response = getAccountDTOResponseEntity(account1);
        ResponseEntity<AccountDTO> getAccount2Response = getAccountDTOResponseEntity(account2);

        Assert.assertEquals(90.0,getAccount1Response.getBody().getBalance(),0.0);
        Assert.assertEquals(110.0,getAccount2Response.getBody().getBalance(),0.0);
    }

    private ResponseEntity<TransactionResponseDTO> doTransaction(AccountTransferTransaction accountTransferTransaction) {
        accountTransferTransaction.setType(TransactionType.TRANSFER);
        HttpEntity<AccountTransferTransaction> reqEntity = new HttpEntity<>(accountTransferTransaction);
        return restTemplate.postForEntity("http://localhost:8080/transaction", reqEntity, TransactionResponseDTO.class);
    }

    @Test
    public void transfer_transaction_insufficient_balance(){
        AccountDTO accountDTO = AccountDTO.builder().balance(100.0).type(AccountType.CONSUMER).build();
        ResponseEntity<AccountDTO> account1 = createAccount(accountDTO);
        ResponseEntity<AccountDTO> account2 = createAccount(accountDTO);

        AccountTransferTransaction accountTransferTransaction = AccountTransferTransaction.builder().fromAccountNum(account1.getBody().getId()).toAccountNum(account2.getBody().getId()).amount(101.0).build();
        ResponseEntity<TransactionResponseDTO> responseEntity = doTransaction(accountTransferTransaction);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

    }

    private ResponseEntity<AccountDTO> getAccountDTOResponseEntity(ResponseEntity<AccountDTO> response1) {
        return restTemplate.getForEntity("http://localhost:8080/accounts" + "/" + response1.getBody().getId(), AccountDTO.class);
    }

    private ResponseEntity<AccountDTO> createAccount(AccountDTO accountDTO) {
        HttpEntity<AccountDTO> reqEntity = new HttpEntity<>(accountDTO);
        return restTemplate.postForEntity("http://localhost:8080/accounts", reqEntity, AccountDTO.class);
    }
}
