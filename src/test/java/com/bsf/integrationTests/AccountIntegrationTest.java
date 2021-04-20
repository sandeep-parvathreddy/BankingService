package com.bsf.integrationTests;

import com.bsf.BankingServiceApplication;
import com.bsf.model.AccountDTO;
import com.bsf.utils.AccountType;
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
public class AccountIntegrationTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void name() {
    }

    @Test
    public void create_account(){
        AccountDTO accountDTO = AccountDTO.builder().balance(100.0).type(AccountType.CONSUMER).build();
        ResponseEntity<AccountDTO> response = getAccountDTOResponseEntity(accountDTO);
        Assert.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assert.assertTrue(response.getBody().getId()!=null);
    }

    private ResponseEntity<AccountDTO> getAccountDTOResponseEntity(AccountDTO accountDTO) {
        HttpEntity<AccountDTO> reqEntity = new HttpEntity<>(accountDTO);
        return restTemplate.postForEntity("http://localhost:8080/accounts", reqEntity, AccountDTO.class);
    }

    @Test
    public void get_account(){
        AccountDTO accountDTO = AccountDTO.builder().balance(100.0).type(AccountType.CONSUMER).build();
        ResponseEntity<AccountDTO> createResponse = getAccountDTOResponseEntity(accountDTO);
        ResponseEntity<AccountDTO> getResponse = restTemplate.getForEntity("http://localhost:8080/accounts" + "/" +createResponse.getBody().getId(),  AccountDTO.class);
        Assert.assertEquals(HttpStatus.OK,getResponse.getStatusCode());
        Assert.assertEquals(100.0,getResponse.getBody().getBalance(),0.0);

    }
}
