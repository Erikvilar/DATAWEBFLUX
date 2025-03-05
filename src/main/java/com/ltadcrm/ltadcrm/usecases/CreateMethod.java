package com.ltadcrm.ltadcrm.usecases;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.Contacts;
import com.ltadcrm.ltadcrm.domain.CostCenter;
import com.ltadcrm.ltadcrm.domain.Details;
import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.Receiving;
import com.ltadcrm.ltadcrm.domain.Users;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CreateItemsDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UsersDTO;
import com.ltadcrm.ltadcrm.repositories.ContactsRepository;
import com.ltadcrm.ltadcrm.repositories.CostCenterRepository;
import com.ltadcrm.ltadcrm.repositories.DetailsRepository;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.repositories.ReceivingRepository;
import com.ltadcrm.ltadcrm.repositories.UsersRepository;
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
public class CreateMethod {

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

    public String create(CreateItemsDTO createItemsDTO) {

        CostCenter costCenter = costCenterRepository.findById(createItemsDTO.getCostCenterID())
                .orElseThrow(() -> new RuntimeException("Nao foi encontrado o ID deste projeto"));

        Users users = usersRepository.findAllById(createItemsDTO.getUsersID())
                .orElseThrow(() -> new RuntimeException("Não foi encontrado o ID deste usuario"));

        try {


            
            Items item = new Items()  ;
            itemsMapper.updateDomainFromDTO(item, createItemsDTO.getItemsDTO());
            itemsRepository.save(item);

            Contacts contacts = new Contacts();
            contactsMapper.updateDomainFromDTO(contacts, createItemsDTO.getContactsDTO());
            contactsRepository.save(contacts);

            item.setUsers(users);
            users.setContacts(contacts);
            usersRepository.save(users);

            Details details = new Details();
            detailsMapper.updateDomainFromDTO(details, createItemsDTO.getDetailsDTO()); 
            item.setDetails(details);
            detailsRepository.save(details);

            item.setCostCenter(costCenter);

            Receiving receiving = new Receiving();
            receivingMapper.updateDomainFromDTO(receiving, createItemsDTO.getReceivingDTO());
         
            receivingRepository.save(receiving);

            return "Item criado";

        } catch (Exception e) {
            return "Ocorreu um erro ao criar o item " + e;
        }
    }

    public CostCenterDTO createCostCenter(CostCenterDTO costCenterDTO) {

        CostCenter costCenter = new CostCenter();
        costCenterMapper.updateDomainFromDTO(costCenter, costCenterDTO);
        return costCenterMapper.toDto(costCenterRepository.save(costCenter));

    }

    public UsersDTO createUsers(UsersDTO usersDTO) {


        Users users = new Users();
        usersMapper.updateDomainFromDTO(users, usersDTO);
        Contacts contactsSave = (contactsMapper.toEntity(usersDTO.getContactsDTO()));
        users.setContacts(contactsRepository.save(contactsSave));
        UsersDTO responseDTO =   usersMapper.toDto(usersRepository.save(users));
        responseDTO.setContactsDTO(contactsMapper.toDto(contactsSave));
        return responseDTO;


    }

}
