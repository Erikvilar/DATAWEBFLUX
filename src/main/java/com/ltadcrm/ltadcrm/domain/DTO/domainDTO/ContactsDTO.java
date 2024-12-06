package com.ltadcrm.ltadcrm.domain.dto.domainDTO;

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

  private Long id;
  private String contactEmail;
  private String contactOccupation;
  private String contactPhone;
  private String lastModification;
  private LocalDateTime updateIn;

}
