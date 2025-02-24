package com.ltadcrm.ltadcrm.usecases;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.CostCenter;
import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterByNameDTO;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.responses.ListWithTotalValues;
import com.ltadcrm.ltadcrm.usecases.mapper.ContactsMapper;
import com.ltadcrm.ltadcrm.usecases.mapper.CostCenterMapper;
import com.ltadcrm.ltadcrm.usecases.mapper.DetailsMapper;
import com.ltadcrm.ltadcrm.usecases.mapper.ItemsMapper;
import com.ltadcrm.ltadcrm.usecases.mapper.ReceivingMapper;
import com.ltadcrm.ltadcrm.usecases.mapper.UsersMapper;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadMethod {

    private final ItemsRepository itemsRepository;
    private final ItemsMapper itemsMapper;
    private final UsersMapper usersMapper;
    private final DetailsMapper detailsMapper;
    private final CostCenterMapper costCenterMapper;
    private final ContactsMapper contactsMapper;
    private final ReceivingMapper receivingMapper;

    public List<ItemDetailDTO> list() throws Exception {
        List<Items> items = itemsRepository.findAll();
        return items.stream().map(entity -> {

            return ItemDetailDTO.fromDto(
                    usersMapper.toDto(entity.getUsers()),
                    itemsMapper.toDto(entity),
                    detailsMapper.toDto(entity.getDetails()),
                    costCenterMapper.toDto(entity.getCostCenter()),
                    contactsMapper.toDto(entity.getUsers().getContacts()),
                    receivingMapper.toDto(entity.getReceiving()));
        }).toList();

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
