package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ltadcrm.ltadcrm.domain.Contacts;

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
  
  @JsonProperty("lastModify")
  private String lastModification;
  @JsonProperty("updateIn")
  private LocalDateTime updateIn;

  public ContactsDTO(Contacts  contacts){
    id = contacts.getId();
    email = contacts.getEmail();
    occupation = contacts.getOccupation();
    phone = contacts.getPhone();
    lastModification = contacts.getLastModification();
    updateIn = LocalDateTime.now();
  }

}
