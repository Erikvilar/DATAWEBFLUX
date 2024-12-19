package com.ltadcrm.ltadcrm.gateway;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterByNameDTO;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.responses.ListWithTotalValues;
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

    public ListWithTotalValues<CostCenterByNameDTO> readItemsByCostCenter(String name) throws Exception {
        try {
            List<Items> items = itemsRepository.findByCostCenterName(name);
            List<CostCenterByNameDTO> costCenterByNameDTOs = items.stream()
                    .map(CostCenterByNameDTO::fromItem)
                    .collect(Collectors.toList());
            Double totalValue = costCenterByNameDTOs.stream()
                    .mapToDouble(CostCenterByNameDTO::getValue)
                    .sum();
            return new ListWithTotalValues<>(costCenterByNameDTOs, totalValue);
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao tentar retornar projetos: " + e);
        }

    }

}
