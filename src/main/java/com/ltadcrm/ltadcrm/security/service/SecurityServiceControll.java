package com.ltadcrm.ltadcrm.security.service;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.security.JWT.TokenService;
import com.ltadcrm.ltadcrm.security.SecurityRepository.AccountRepository;
import com.ltadcrm.ltadcrm.security.controller.authentication.AuthenticationDTO;
import com.ltadcrm.ltadcrm.security.controller.authentication.LogoutDTO;
import com.ltadcrm.ltadcrm.security.controller.authentication.RegisterDTO;
import com.ltadcrm.ltadcrm.security.controller.authentication.ToFrontDTO;
import com.ltadcrm.ltadcrm.security.entity.Account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityServiceControll {

    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final TokenService tokenService;

    public ToFrontDTO loginMethod(AuthenticationDTO data) throws Exception {
        try {
            Account account = accountRepository.findAccountByLogin(data.login());
            System.out.println(account.getIsLogged());
            if (account.getIsLogged()) {
                account.setIsLogged(false);
                throw new IllegalAccessError("Usuário ja esta logado");
            }

            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generatedToken((Account) auth.getPrincipal());

            account.setIsLogged(true);
            Account accountLogged = accountRepository.save(account);
            var userLogged = new ToFrontDTO(accountLogged.getAvatar(), accountLogged.getLogin(), token,
                    accountLogged.getRole().toString(), accountLogged.getIsLogged());
            log.info("Usuario {} fez login no sistema ", data.login());

            return userLogged;
        } catch (Exception e) {
            throw new IllegalAccessError("Ocorreu um erro ao realizar login do usuario " + e);
        }

    }

    public RegisterDTO registerMethod(RegisterDTO register) throws Exception {
        try {
            if (this.accountRepository.findByLogin(register.login()) != null) {
                log.info("Processo de cadastro de novo usuario capturado ");
                throw new IllegalAccessException("Usuário já cadastrado no sistema");
            }

            String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
            Account user = new Account(register.login(), encryptedPassword, register.avatar(), register.isLogged(),
                    register.role());

            this.accountRepository.save(user);
            log.info("Usuário ,{} foi registrado no sistema", register.login());
            return new RegisterDTO(register.login(), encryptedPassword, register.avatar(), register.isLogged(),
                    register.role());
        } catch (Exception e) {
            throw new IllegalAccessError("Ocorreu um erro ao fazer o cadastro deste usuário no sistema" + e);
        }
    }

    public LogoutDTO logoutMethod(LogoutDTO logout) throws Exception {
        Account account = accountRepository.findAccountByLogin(logout.login());

        account.setIsLogged(false);
        Account accountLogout = accountRepository.save(account);

        return new LogoutDTO(accountLogout.getLogin(), accountLogout.getIsLogged());
    }

}
