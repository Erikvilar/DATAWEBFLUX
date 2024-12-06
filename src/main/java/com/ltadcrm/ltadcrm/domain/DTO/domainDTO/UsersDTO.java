package com.ltadcrm.ltadcrm.domain.dto.domainDTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsersDTO {
        
        private Long id;
        private String userName;
        private String userType;
        private String lastModification;
        private LocalDateTime updateIn;

      
}