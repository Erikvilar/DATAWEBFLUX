package com.ltadcrm.ltadcrm.security.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final Map<String, String> usuariosLogados = new ConcurrentHashMap<>();

    public ToFrontDTO loginMethod(AuthenticationDTO data) throws Exception {
        try {
            log.info("Tentativa de login para {}", data.login());
    
            if (data.login() == null || data.password() == null) {
                log.warn("Dados de login inválidos: login={}, password={}", data.login(), data.password());
                throw new IllegalArgumentException("Credenciais inválidas!");
            }
    
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);
    
  
            if (auth == null || auth.getPrincipal() == null) {
                log.warn("Autenticação falhou para {}", data.login());
                throw new IllegalArgumentException("Autenticação falhou!");
            }
    
            if (!(auth.getPrincipal() instanceof Account)) {
                log.warn("Objeto principal não é do tipo esperado! Principal: {}", auth.getPrincipal().getClass().getName());
                throw new IllegalArgumentException("Usuário inválido!");
            }
    
            Account account = (Account) auth.getPrincipal();
    
            if (account == null || account.getLogin() == null) {
                log.error("Conta autenticada é inválida ou login está null!");
                throw new IllegalStateException("Erro interno: conta inválida.");
            }
    
         
            if (usuariosLogados.containsKey(account.getLogin())) {
                log.warn("Usuário {} já está logado e tentou novo acesso!", account.getLogin());
                throw new IllegalStateException("Usuário já autenticado!");
            }
    
            log.info("Buscando conta no banco para {}", account.getLogin());
            var login = accountRepository.findAccountByLogin(account.getLogin());
    
            if (login == null) {
                log.warn("Conta não encontrada para {}", account.getLogin());
                throw new UsernameNotFoundException("Conta não encontrada!");
            }
    
            var token = tokenService.generatedToken(account);
            if (token == null) {
                log.error("Erro ao gerar token para {}", account.getLogin());
                throw new IllegalStateException("Falha ao gerar token!");
            }
    
            var userLogged = new ToFrontDTO(login.getAvatar(), login.getLogin(), token, login.getRole().toString());
            usuariosLogados.put(account.getLogin(), "LOGADO");
            log.info("Usuário {} fez login com sucesso.", account.getLogin());
    
            return userLogged;
        } catch (AuthenticationException e) {
            log.error("Falha na autenticação do usuário {}: {}", data.login(), e.getMessage());
            throw new IllegalStateException("Falha na autenticação!");
        } catch (Exception e) {
            log.error("Erro inesperado ao autenticar {}: {}", data.login(), e.getMessage());
            throw new RuntimeException("Erro ao autenticar usuário!", e);
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
            return new RegisterDTO(register.login(), encryptedPassword, register.avatar(), register.role());
        } catch (Exception e) {
            throw new IllegalAccessError("Ocorreu um erro ao fazer o cadastro deste usuário no sistema" + e);
        }
    }

    public void logout(String login) {
        if (login == null || login.isEmpty()) {
            log.warn("Tentativa de logout com login inválido!");
            throw new IllegalArgumentException("Login inválido!");
        }
    
        // Verifica se o usuário está logado antes de remover
        if (!usuariosLogados.containsKey(login)) {
            log.warn("Usuário {} tentou fazer logout, mas não estava logado!", login);
            throw new IllegalStateException("Usuário não está logado!");
        }
    
        // Remove o usuário do mapa de usuários logados
        usuariosLogados.remove(login);
        log.info("Usuário {} fez logout com sucesso.", login);
    }
}
