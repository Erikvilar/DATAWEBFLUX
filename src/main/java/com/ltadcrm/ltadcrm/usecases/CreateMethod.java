package com.ltadcrm.ltadcrm.usecases;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.Contacts;
import com.ltadcrm.ltadcrm.domain.CostCenter;
import com.ltadcrm.ltadcrm.domain.Details;
import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.Receiving;
import com.ltadcrm.ltadcrm.domain.Users;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemsDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;
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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

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

 
    public String create(ItemsDTO itemsDTO) {
        try {

            Items item = new Items();
            itemsMapper.updateDomainFromDTO(item, itemsDTO);
            Contacts contacts = contactsRepository.findById(itemsDTO.getUsersDTO().getContactsDTO().getId()).get();
            Users user = usersRepository.findById(itemsDTO.getUsersDTO().getId())
                    .orElseGet(() -> {
                        Users newUser = usersMapper.toEntity(itemsDTO.getUsersDTO());
                        newUser.setContacts(contacts);
                        return newUser;
                    });

            Details details = detailsRepository.findById(itemsDTO.getDetailsDTO().getId())
                    .orElseGet(() -> detailsMapper.toEntity(itemsDTO.getDetailsDTO()));

            CostCenter costCenter = costCenterRepository.findById(itemsDTO.getCostCenterDTO().getId())
                    .orElseGet(() -> costCenterMapper.toEntity(itemsDTO.getCostCenterDTO()));

            Receiving receiving = receivingRepository.findById(itemsDTO.getReceivingDTO().getReceivingID())
                    .orElseGet(() -> receivingMapper.toEntity(itemsDTO.getReceivingDTO()));

            item.setUsers(user);
            item.setDetails(details);
            item.setCostCenter(costCenter);
            item.setReceiving(receiving);
            usersRepository.save(user);
            itemsRepository.save(item);

            return "HTTP RESPONSE NOTIFY DATAWEBFLUX\nID: " + item.getId() + "\nITEM: "
                    + itemsDTO.getDetailsDTO().getDescription() + "\nResponse status server: OK\nDATE: "
                    + LocalDate.now();

        } catch (Exception e) {
            return "Ocorreu um erro ao criar o item " + e;
        }
    }

}
