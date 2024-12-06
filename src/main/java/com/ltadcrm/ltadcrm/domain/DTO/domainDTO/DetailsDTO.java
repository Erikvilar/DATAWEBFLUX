package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

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
  
  @JsonProperty("id_descricao")
  private Long id;
  @JsonProperty("marca_descricao")
  private String brand;
  @JsonProperty("descricao_item")
  private String description;
  @JsonProperty("localizacao_descricao")
  private String local;
  @JsonProperty("modelo_descricao")
  private String model;
  @JsonProperty("serie_descricao")
  private String serial;
  @JsonProperty("lastModify")
  private String lastModification;
  @JsonProperty("updateIn")
  private LocalDateTime updateIn;

  public DetailsDTO(Details descriptions){
    id = descriptions.getId();
    brand = descriptions.getBrand();
    description = descriptions.getDescription();
    local = descriptions.getLocal();
    model = descriptions.getModel();
    serial = descriptions.getSerial();
    lastModification = descriptions.getLastModification();
    updateIn = LocalDateTime.now();


  }

}
