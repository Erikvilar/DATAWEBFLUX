package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;



import com.ltadcrm.ltadcrm.domain.Items;

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
    private Double value;
   

     public static CostCenterByNameDTO fromItem(Items item) {
        return new CostCenterByNameDTO(
            item.getId(),
            item.getNumber(),
            item.getCostCenter().getName(),
            item.getCostCenter().getIdentification(),
            item.getDetails().getDescription(),
            item.getNfInvoice(),
            item.getValue()
        );
    }
}
