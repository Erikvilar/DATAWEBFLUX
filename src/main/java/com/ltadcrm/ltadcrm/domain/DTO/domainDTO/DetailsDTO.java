package com.ltadcrm.ltadcrm.domain.dto.domainDTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ltadcrm.ltadcrm.domain.Details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsDTO {
  


  
  private Long id;
  private String brand;
  private String description;
  private String location;
  private String model;
  private String series;
  private String lastModification;
  private LocalDateTime updateIn;



}
