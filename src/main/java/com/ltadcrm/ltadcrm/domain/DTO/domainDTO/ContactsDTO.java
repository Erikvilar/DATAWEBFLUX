package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactsDTO {

  @JsonProperty("id_contato")
  private Long id;

  @JsonProperty("email_contato")
  private String email;

  @JsonProperty("ocupacao_contato")
  private String occupation;

  @JsonProperty("telefone_contato")
  private String phone;

  @JsonProperty("responsavel_geral")
  private String responsibleGeneral;

  @JsonProperty("lastModify")
  private String lastModification;
  @JsonProperty("updateIn")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime updateIn;


}
