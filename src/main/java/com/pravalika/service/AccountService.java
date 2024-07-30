package com.pravalika.service;

import com.pravalika.dto.AccountDto;


public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id,double amount);
    void deleteAccount(Long id);
}
