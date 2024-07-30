package com.pravalika.service;

import com.pravalika.dto.AccountDto;
import com.pravalika.entity.Account;
import com.pravalika.mapper.AccountMapper;
import com.pravalika.repository.AccountRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Secured("ROLE_USER")
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
    @Secured("ROLE_USER")
    @Override
    public AccountDto getAccountById(Long id) {
         Account account =accountRepository.findById(id).orElseThrow(()->
                 new RuntimeException("Account does not exist"));
         return AccountMapper.mapToAccountDto(account);
    }
    @Secured("ROLE_ADMIN")
    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =accountRepository.findById(id).orElseThrow(()->
                new RuntimeException("Account does not exist"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
    @Secured("ROLE_ADMIN")
    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account doesnt exist"));
        accountRepository.deleteById(id);
    }
}
