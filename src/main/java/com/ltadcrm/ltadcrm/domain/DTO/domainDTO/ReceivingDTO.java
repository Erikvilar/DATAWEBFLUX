package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReceivingDTO {
    private Long receivingID;
    private Long receivingCode;
    private String lotation;
    private String local;
    private String empSiafi;
}
