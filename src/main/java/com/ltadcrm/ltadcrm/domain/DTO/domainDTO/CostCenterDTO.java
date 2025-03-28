package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ltadcrm.ltadcrm.domain.CostCenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostCenterDTO {
  @JsonProperty("id_centro_custo")
  private Long id;

  @JsonProperty("nome_centro_custo")
  private String name;

  @JsonProperty("identificacao_centro_custo")
  private String identification;

  @JsonProperty("data_inicio_centro_custo")
  private Date initialDate;

  @JsonProperty("data_fim_centro_custo")
  private Date endDate;
  @JsonProperty("lastModify")
  private String lastModification;
  @JsonProperty("updateIn")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime updateIn;

  public CostCenterDTO(CostCenter costCenter) {
    id = costCenter.getId();
    name = costCenter.getName();
    identification = costCenter.getIdentification();
    initialDate = costCenter.getInitialDate();
    endDate = costCenter.getEndDate();
    lastModification = costCenter.getLastModification();
    updateIn = LocalDateTime.now();
  }
}
