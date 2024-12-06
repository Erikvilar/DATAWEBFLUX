package com.ltadcrm.ltadcrm.gateway;


import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.domain.dto.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.gateway.strategy.ItemDetailsDTOConvertImpl;

import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindAllEntities {
    
    private final ItemsRepository itemsRepository;
    private final ItemDetailsDTOConvertImpl convert;
 

}
