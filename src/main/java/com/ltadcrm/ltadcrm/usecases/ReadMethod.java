package com.ltadcrm.ltadcrm.usecases;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.Receiving;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterByNameDTO;
import com.ltadcrm.ltadcrm.repositories.ContactsRepository;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.repositories.ReceivingRepository;
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
    private final ReceivingRepository receivingRepository;
    private final ContactsRepository contactsRepository;
    @Transactional
    public List<ItemDetailDTO> list() throws Exception {
        try {
            List<Receiving> receivings = receivingRepository.findAll();
            if (receivings.isEmpty()) {
                System.out.println("Nenhum receiving encontrado");
            }
    
            return receivings.stream()
                .flatMap(receiving -> {
                    List<Items> itemsList = receiving.getItems();
                    if (itemsList == null || itemsList.isEmpty()) {
                        System.out.println("Nenhum item encontrado para o receiving ID: " + receiving.getReceivingID());
                    }
    
                    return itemsList.stream().map(item -> 
                        ItemDetailDTO.fromDto(  
                            usersMapper.toDto(item.getUsers()),
                            itemsMapper.toDto(item),
                            detailsMapper.toDto(item.getDetails()),
                            costCenterMapper.toDto(item.getCostCenter()),
                            contactsMapper.toDto(item.getUsers().getContacts()), 
                            receivingMapper.toDto(receiving)
                        )
                    );
                })
                .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Erro ao acessar os dados: " + e.getMessage());
            throw new Exception("Erro ao acessar os dados", e);
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
