package com.ltadcrm.ltadcrm.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltadcrm.ltadcrm.security.controller.authentication.AuthenticationDTO;
import com.ltadcrm.ltadcrm.security.controller.authentication.LogoutDTO;
import com.ltadcrm.ltadcrm.security.controller.authentication.RegisterDTO;

import com.ltadcrm.ltadcrm.security.controller.authentication.ToFrontDTO;
import com.ltadcrm.ltadcrm.security.service.SecurityServiceControll;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final SecurityServiceControll security;

    @PostMapping("/login")
    public ResponseEntity<ToFrontDTO> login(@RequestBody @Valid AuthenticationDTO data) throws Exception {
        try{
            return new ResponseEntity<>(security.loginMethod(data), HttpStatus.ACCEPTED); 
      
        }catch(IllegalAccessError e){
            return ResponseEntity.status(409).build();
        }
       
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<RegisterDTO> register(@RequestBody @Valid RegisterDTO register) throws Exception {
        if(register.password().length() < 5 || register.login().length() < 5 ){
            ResponseEntity.badRequest().body("A password e o login deve conter pelo menos 6 caracteres.");
            throw new Exception("Usuario tentou fazer registro de novo usuario invalido, com menos de 6 caracters.");
        }
        return new ResponseEntity<>(security.registerMethod(register), HttpStatus.ACCEPTED);

    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutDTO> postMethodName(@RequestBody LogoutDTO logout) throws Exception {
        return new ResponseEntity<>(security.logoutMethod(logout), HttpStatus.OK);

    }

}
