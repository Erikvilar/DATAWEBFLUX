package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReceivingListDTO {

    private ReceivingDTO receivingDTO;
    
    private List<ItemsDTO> items;

    private CostCenterDTO costCenterDTO;


}
