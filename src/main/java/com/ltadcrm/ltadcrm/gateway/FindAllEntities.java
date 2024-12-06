package com.ltadcrm.ltadcrm.gateway;


import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.gateway.strategy.ItemDetailsDTOConvertImpl;

import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindAllEntities {
    
    private final ItemsRepository itemsRepository;
    private final ItemDetailsDTOConvertImpl convert;
 

    public List<ItemDetailDTO> list() throws Exception {
        try {

            List<ItemDetailDTO> dtos = new ArrayList<>();
            for (Tuple tuple : itemsRepository.findAllItemsDTOs()) {
                ItemDetailDTO dto = convert.convert(tuple);
                dtos.add(dto);
            }
            return dtos;
        } catch (Exception e) {
            throw new Exception("Current error in ItemService " + e);
        }
    }
}
