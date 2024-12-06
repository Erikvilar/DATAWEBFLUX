package com.ltadcrm.ltadcrm.domain.dto.domainDTO;


import java.time.LocalDateTime;
import java.util.Date;



import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailDTO {


    private Long id;
    private String userName;
    private String userType;
    private Long itemId;
    private String nfInvoice;
    private String code;
    private String observation;
    private String imagePath;
    private String orderOrigin;
    private Long sde;
    private String status;
    private Double value;
    private Long descriptionId;
    private String brand;
    private String description;
    private String location;
    private String model;
    private String series;
    private Long costCenterId;
    private String costCenterName;
    private String costCenterIdentification;
    private Date costCenterStartDate;
    private Date costCenterEndDate;
    private Long contactId;
    private String contactEmail;
    private String contactOccupation;
    private String contactPhone;
    private String lastModification;
    private LocalDateTime updateIn; 
}