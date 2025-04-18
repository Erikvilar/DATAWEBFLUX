package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;


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
    @JsonProperty("lotacao")
    private String lotation;
    @JsonProperty("fornecedor")
    private String supplier;
    @JsonProperty("email_fornecedor")
    private String email;
    @JsonProperty("termoPDF")
    private String pdfTerm;
    @JsonProperty("pedidoPDF")
    private String pdfOrder;
    @JsonProperty("pedido")
    private String orderCode;
    @JsonProperty("empSIAFI")
    private String empSIAFI;
    
}
