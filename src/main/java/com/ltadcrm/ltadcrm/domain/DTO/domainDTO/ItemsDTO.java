package com.ltadcrm.ltadcrm.domain.dto.domainDTO;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ItemsDTO{

    @JsonProperty("id_item")
    private Long id;

    @JsonProperty("nf_invoice_item")
    private String nfInvoice;

    @JsonProperty("codigo_item")
    private String number;

    @JsonProperty("observacao_item")
    private String observation;

    @JsonProperty("caminho_imagem_item")
    private String pathImage;

    @JsonProperty("pedido_origem")
    private String order;

    @JsonProperty("sde_item")
    private Long sde;

    @JsonProperty("status_item")
    private String status;

    @JsonProperty("valor_item")
    private Double value;

    @JsonProperty("lastModify")
    private String lastModification;

    @JsonProperty("updateIn")
    private LocalDateTime updateIn;



 
}