package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsersDTO {

        @JsonProperty("id_usuario")
        private Long userID;

        @JsonProperty("nome_usuario")
        private String userName;

        @JsonProperty("tipo_usuario")
        private String userType;

        @JsonProperty("email_usuario")
        private String emailUser;

        @JsonProperty("telefone_usuario")
        private String phoneUser;

}