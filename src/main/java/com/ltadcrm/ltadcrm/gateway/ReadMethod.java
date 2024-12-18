package com.ltadcrm.ltadcrm.gateway;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemsDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterByNameDTO;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.gateway.strategy.ItemDetailsDTOConvertImpl;

import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadMethod {

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

    public List<CostCenterByNameDTO> readItemsByCostCenter(String name) throws Exception {
        try {
            List<Items> items = itemsRepository.findByCostCenterName(name);

            return items.stream()
                    .map(item -> new CostCenterByNameDTO(item.getId(), item.getNumber(), item.getCostCenter().getName(),
                            item.getCostCenter().getIdentification(), item.getDetails().getDescription(),
                            item.getNfInvoice()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao tentar retornar projetos: " + e);
        }

    }

}
