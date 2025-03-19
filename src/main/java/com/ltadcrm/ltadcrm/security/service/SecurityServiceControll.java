package com.ltadcrm.ltadcrm.security.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.security.JWT.TokenService;
import com.ltadcrm.ltadcrm.security.SecurityRepository.AccountRepository;
import com.ltadcrm.ltadcrm.security.controller.authentication.AuthenticationDTO;
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
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generatedToken((Account) auth.getPrincipal());
            var login = accountRepository.findAccountByLogin(data.login());
            var userLogged = new ToFrontDTO(login.getAvatar(), login.getLogin(),  token, login.getRole().toString());
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
            Account user = new Account(register.login(), encryptedPassword, register.avatar(), register.role());
           
            this.accountRepository.save(user);
            log.info("Usuário ,{} foi registrado no sistema", register.login());
            return new RegisterDTO(register.login(), encryptedPassword,register.avatar(), register.role());
        } catch (Exception e) {
            throw new IllegalAccessError("Ocorreu um erro ao fazer o cadastro deste usuário no sistema"+e);
        }
    }
}
