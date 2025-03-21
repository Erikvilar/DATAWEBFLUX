package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;





import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateDTO {

 
    private ItemsDTO itemsDTO;
    private UsersDTO usersDTO;
    private ResponsibleDTO responsibleDTO;
    private DetailsDTO detailsDTO;
    private CostCenterDTO costCenterDTO;
    private ReceivingDTO receivingDTO;

    



}
