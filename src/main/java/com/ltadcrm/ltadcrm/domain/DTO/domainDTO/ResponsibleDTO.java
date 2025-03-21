package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponsibleDTO {

    @JsonProperty("id_responsavel_geral")
    private Long responsibleID;

    @JsonProperty("nome_responsavel_geral")
    private String nameResponsible;

    @JsonProperty("ocupacao_responsavel")
    private String occupationResponsible;
  
    @JsonProperty("email_responsavel_geral")
    private String emailResponsible;

    @JsonProperty("telefone_responsavel_geral")
    private String phoneResponsible;

     

}
