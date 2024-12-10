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
import com.ltadcrm.ltadcrm.repositories.ContactsRepository;
import com.ltadcrm.ltadcrm.repositories.CostCenterRepository;
import com.ltadcrm.ltadcrm.repositories.DetailsRepository;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
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

    public ResponseEntity<String> create(UpdateDTO updateDTO) {
        try {
 
            Items item = new Items();
            item.setNumber(updateDTO.getItemsDTO().getNumber());
            item.setObservation(updateDTO.getItemsDTO().getObservation());
            item.setPathImage(updateDTO.getItemsDTO().getPathImage());
            item.setSde(updateDTO.getItemsDTO().getSde());
            item.setOrder(updateDTO.getItemsDTO().getOrder());
            item.setProcessSEI(updateDTO.getItemsDTO().getProcessSEI());
            item.setStatus(updateDTO.getItemsDTO().getStatus());
            item.setNfInvoice(updateDTO.getItemsDTO().getNfInvoice());
            item.setValue(updateDTO.getItemsDTO().getValue());
            item.setLastModification(updateDTO.getItemsDTO().getLastModification());
            item.setUpdateIn(updateDTO.getUpdateAt());

     
            Users user = new Users();
            user.setUserName(updateDTO.getUsersDTO().getUserName());
            user.setUserType(updateDTO.getUsersDTO().getUserType());
            user.setLastModification(updateDTO.getUsersDTO().getLastModification());

            Details description = new Details();
            description.setBrand(updateDTO.getDetailsDTO().getBrand());
            description.setDescription(updateDTO.getDetailsDTO().getDescription());
            description.setLocal(updateDTO.getDetailsDTO().getLocal());
            description.setModel(updateDTO.getDetailsDTO().getModel());
            description.setSerial(updateDTO.getDetailsDTO().getSerial());
            description.setLastModification(updateDTO.getDetailsDTO().getLastModification());

            CostCenter costCenter = new CostCenter();
            costCenter.setName(updateDTO.getCostCenterDTO().getName());
            costCenter.setIdentification(updateDTO.getCostCenterDTO().getIdentification());
            costCenter.setInitialDate(updateDTO.getCostCenterDTO().getInitialDate());
            costCenter.setEndDate(updateDTO.getCostCenterDTO().getEndDate());
            costCenter.setLastModification(updateDTO.getCostCenterDTO().getLastModification());

            Contacts contact = new Contacts();
            contact.setEmail(updateDTO.getContactsDTO().getEmail());
            contact.setOccupation(updateDTO.getContactsDTO().getOccupation());
            contact.setPhone(updateDTO.getContactsDTO().getPhone());
            contact.setLastModification(updateDTO.getContactsDTO().getLastModification());
            
            Receiving receiving = new Receiving();
            receiving.setReceivingCode(updateDTO.getReceivingDTO().getReceivingCode());
            receiving.setLocal(updateDTO.getReceivingDTO().getLocal());
            receiving.setEmpSiafi(updateDTO.getReceivingDTO().getEmpSiafi());
        
            item.setUsers(user);
            item.setDetails(description);
            item.setCostCenter(costCenter);
            item.setReceiving(receiving);

            usersRepository.save(user);
            detailsRepository.save(description);
            costCenterRepository.save(costCenter);
            contactsRepository.save(contact);
            itemsRepository.save(item);
            return ResponseEntity.ok("Item criado");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao criar o item "+e);
        }
    }

   

}
