package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private String codigo_item;
    private MultipartFile firstImage;
    private MultipartFile secondImage;

}
