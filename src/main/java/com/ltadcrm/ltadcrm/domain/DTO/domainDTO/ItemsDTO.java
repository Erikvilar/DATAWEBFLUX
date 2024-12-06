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
public class ItemsDTO {


    private Long id;
    private String nfInvoice;
    private String code;
    private String especification;
    private String imagePath;
    private String order;
    private Long sde;
    private String status;
    private Double value;
    private String lastModification;
    private LocalDateTime updateIn;

}