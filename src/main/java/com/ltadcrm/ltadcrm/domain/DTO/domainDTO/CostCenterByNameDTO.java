package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class CostCenterByNameDTO {
   
    private Long id;
    private String number;
    private String costCenterName;
    private String identification;
    private String description;
    private String nfInvoice;
   
}
