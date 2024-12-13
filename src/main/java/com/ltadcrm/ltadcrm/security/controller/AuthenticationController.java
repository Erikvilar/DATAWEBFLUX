package com.ltadcrm.ltadcrm.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltadcrm.ltadcrm.security.controller.authentication.AuthenticationDTO;
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
    public ResponseEntity<ToFrontDTO> login(@RequestBody @Valid AuthenticationDTO data) throws Exception{
        return new ResponseEntity<>(security.loginMethod(data), HttpStatus.ACCEPTED);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<RegisterDTO> register(@RequestBody @Valid RegisterDTO register) throws Exception {
        return new ResponseEntity<>(security.registerMethod(register), HttpStatus.ACCEPTED);

    }
}
