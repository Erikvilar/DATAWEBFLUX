package com.ltadcrm.ltadcrm.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltadcrm.ltadcrm.domain.Account;
import com.ltadcrm.ltadcrm.domain.dto.authentication.AuthenticationDTO;
import com.ltadcrm.ltadcrm.domain.dto.authentication.RegisterDTO;
import com.ltadcrm.ltadcrm.security.JWT.TokenService;
import com.ltadcrm.ltadcrm.security.accountRepository.AccountRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    AuthenticationManager authenticationManager;
    AccountRepository accountRepository;
    TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, AccountRepository accountRepository,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.tokenService = tokenService;

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data, HttpServletResponse response) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generatedToken((Account) auth.getPrincipal());
        
            return ResponseEntity.ok(token);

        } catch (Exception e) {
            return new ResponseEntity<>("erro >> " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO register) {
        if (this.accountRepository.findByLogin(register.login()) != null)
            
            return new ResponseEntity<>("Usuário já existente localizado", HttpStatus.BAD_REQUEST);

        String encrypt = new BCryptPasswordEncoder().encode(register.password());
        Account user = new Account(register.login(), encrypt, register.role());

        this.accountRepository.save(user);
        return new ResponseEntity<>("Usuario cadastrado com sucesso no sistema", HttpStatus.OK);

    }

    @GetMapping("/teste")
    public ResponseEntity<String> getMethodName() {
        try {
            return new ResponseEntity<>("Teste de login", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Nao autorizado " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
