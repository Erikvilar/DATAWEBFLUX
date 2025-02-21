package com.ltadcrm.ltadcrm.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemsDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterByNameDTO;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;

import com.ltadcrm.ltadcrm.responses.ListWithTotalValues;
import com.ltadcrm.ltadcrm.usecases.mapper.ItemsMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadMethod {
    private final ItemsMapper itemsMapper;
    private final ItemsRepository itemsRepository;

    public List<ItemsDTO> list() {
        List<Items> items = itemsRepository.findAll();
        return itemsMapper.toDTOList(items);
    }

    public ListWithTotalValues<CostCenterByNameDTO> readItemsByCostCenter(String name) throws Exception {
        try {
            List<Items> items = itemsRepository.findByCostCenterName(name);
            List<CostCenterByNameDTO> costCenterByNameDTOs = items.stream()
                    .map(CostCenterByNameDTO::fromItem).toList();

            Double totalValue = costCenterByNameDTOs.stream()
                    .mapToDouble(CostCenterByNameDTO::getValue)
                    .sum();
            return new ListWithTotalValues<>(costCenterByNameDTOs, totalValue);
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao tentar retornar projetos: " + e);
        }

    }

}
