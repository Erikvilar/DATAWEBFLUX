package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReceivingDTO {
    @JsonProperty("id_recebimento")
    private Long receivingID;
    @JsonProperty("termo")
    private Long receivingCode;
    @JsonProperty("lotação")
    private String lotation;
    @JsonProperty("local")
    private String local;
    @JsonIgnore
    @JsonProperty("empSIAFI")
    private String empSiafi;
    
}
