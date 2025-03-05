package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
public class ReceivingListDTO {

    private ReceivingDTO receivingDTO;
    
    private List<ItemsDTO> items;
}
