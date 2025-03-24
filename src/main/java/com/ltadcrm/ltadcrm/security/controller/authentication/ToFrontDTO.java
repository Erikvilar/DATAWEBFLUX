package com.ltadcrm.ltadcrm.security.controller.authentication;

public record ToFrontDTO(String avatar, String login, String token, String role, Boolean isLogged) {
    
}
