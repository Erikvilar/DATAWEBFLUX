package com.ltadcrm.ltadcrm.gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.Contacts;
import com.ltadcrm.ltadcrm.domain.CostCenter;
import com.ltadcrm.ltadcrm.domain.Details;
import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.Receiving;
import com.ltadcrm.ltadcrm.domain.Users;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;
import com.ltadcrm.ltadcrm.gateway.mapper.ContactsMapper;
import com.ltadcrm.ltadcrm.gateway.mapper.CostCenterMapper;
import com.ltadcrm.ltadcrm.gateway.mapper.DetailsMapper;
import com.ltadcrm.ltadcrm.gateway.mapper.ItemsMapper;
import com.ltadcrm.ltadcrm.gateway.mapper.ReceivingMapper;
import com.ltadcrm.ltadcrm.gateway.mapper.UsersMapper;
import com.ltadcrm.ltadcrm.repositories.ContactsRepository;
import com.ltadcrm.ltadcrm.repositories.CostCenterRepository;
import com.ltadcrm.ltadcrm.repositories.DetailsRepository;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.repositories.ReceivingRepository;
import com.ltadcrm.ltadcrm.repositories.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateAllIEntities {

    private final ItemsRepository itemsRepository;
    private final UsersRepository usersRepository;
    private final CostCenterRepository costCenterRepository;
    private final DetailsRepository detailsRepository;
    private final ContactsRepository contactsRepository;
    private final ReceivingRepository receivingRepository;
    private final ItemsMapper itemsMapper;
    private final UsersMapper usersMapper;
    private final DetailsMapper detailsMapper;
    private final CostCenterMapper costCenterMapper;
    private final ContactsMapper contactsMapper;
    private final ReceivingMapper receivingMapper;

    public ResponseEntity<String> create(UpdateDTO updateDTO) {
        try {

            Items item = new Items();
            itemsMapper.updateDomainFromDTO(item, updateDTO.getItemsDTO());
            itemsRepository.save(item); 

            Users user = new Users(); 
            usersMapper.updateDomainFromDTO(user, updateDTO.getUsersDTO());
            item.setUsers(user);
            usersRepository.save(user); 

            Details details = new Details();
            detailsMapper.updateDomainFromDTO(details, updateDTO.getDetailsDTO());
            item.setDetails(details);
            detailsRepository.save(details); 

            Contacts contacts =  new Contacts();
            contactsMapper.updateDomainFromDTO(contacts, updateDTO.getContactsDTO());
            contactsRepository.save(contacts);

            CostCenter costCenter = new CostCenter();
            costCenterMapper.updateDomainFromDTO(costCenter, updateDTO.getCostCenterDTO());
            item.setCostCenter(costCenter);
            costCenterRepository.save(costCenter);

            Receiving receiving = new Receiving();
            receivingMapper.updateDomainFromDTO(receiving, updateDTO.getReceivingDTO());
            item.setReceiving(receiving);
            receivingRepository.save(receiving);

            return ResponseEntity.ok("Item criado");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao criar o item " + e);
        }
    }

}
