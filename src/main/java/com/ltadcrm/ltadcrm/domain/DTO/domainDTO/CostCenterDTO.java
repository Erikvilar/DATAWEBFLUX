package com.ltadcrm.ltadcrm.domain.dto.domainDTO;

import java.sql.Date;
import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostCenterDTO {
 


  private Long id;
  private String costCenterName;
  private String costCenterIdentification;
  private Date costCenterStartDate;
  private Date costCenterEndDate;
  private String lastModification;
  private LocalDateTime updateIn;

}
