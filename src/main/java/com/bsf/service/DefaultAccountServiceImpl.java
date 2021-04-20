package com.bsf.service;

import com.bsf.dao.Account;
import com.bsf.mapper.AccountMapper;
import com.bsf.model.AccountDTO;
import com.bsf.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by sandeepreddy on 19/04/21.
 */
@Service
public class DefaultAccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        Account account= AccountMapper.toAccount(accountDTO);
        Long accountId = buildAccountId(accountDTO);
        account.setId(accountId);
        accountRepository.save(account);
        return buildAccountResponse(account);
    }

    private AccountDTO buildAccountResponse(Account account) {
        return AccountMapper.toAccountDTO(account);
    }

    private Long buildAccountId(AccountDTO accountDTO) {
        return Objects.nonNull(accountDTO.getId()) ? accountDTO.getId() : System.currentTimeMillis();
    }

    @Override
    public AccountDTO get(Long id) {
        return buildAccountResponse(getAccount(id));
    }

    @Override
    @Transactional
    public AccountDTO credit(Long id, double amount) {
        Account account = getAccount(id);
        double currentBalance = account.getBalance();
        account.setBalance(currentBalance+amount);
        accountRepository.save(account);
        return buildAccountResponse(account);

    }

    private Account getAccount(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow(() -> new EntityNotFoundException("No account present with id : " +id));

    }

    @Override
    @Transactional
    public AccountDTO debit(Long id, double amount) {
        Account account = getAccount(id);
        double currentBalance = account.getBalance();
        if(currentBalance<amount){
            throw new RuntimeException("Account doesn't have enough balance");
        }
        account.setBalance(currentBalance-amount);
        accountRepository.save(account);
        return buildAccountResponse(account);
    }
}
