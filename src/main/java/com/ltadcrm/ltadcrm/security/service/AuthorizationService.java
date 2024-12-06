package com.ltadcrm.ltadcrm.security.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.security.accountRepository.AccountRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    AccountRepository accountRepository;

    public AuthorizationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.accountRepository.findByLogin(username);
    }

}
