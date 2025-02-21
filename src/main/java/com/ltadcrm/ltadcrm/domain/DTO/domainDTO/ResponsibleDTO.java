package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponsibleDTO {

    private Long id;
    private String name;
    private String occupation;

}
 