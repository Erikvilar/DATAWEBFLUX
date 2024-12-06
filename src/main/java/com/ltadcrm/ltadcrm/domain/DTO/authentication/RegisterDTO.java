package com.ltadcrm.ltadcrm.domain.dto.authentication;

import com.ltadcrm.ltadcrm.domain.Enums.AccountRoles;

public record RegisterDTO(String login, String password, AccountRoles role) {

}