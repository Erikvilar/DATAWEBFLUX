package com.ltadcrm.ltadcrm.security.controller.authentication;


public record AuthenticationDTO(String login, Boolean isLogged, String password) {}
