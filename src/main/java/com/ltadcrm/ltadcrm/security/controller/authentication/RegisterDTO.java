package com.ltadcrm.ltadcrm.security.controller.authentication;

import com.ltadcrm.ltadcrm.security.entity.Enums.AccountRoles;

public record RegisterDTO(String login, String password, AccountRoles role) {

}