
package com.ltadcrm.ltadcrm.gateway;


import org.springframework.context.ApplicationEventPublisher;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;
import com.ltadcrm.ltadcrm.events.Items.ItemUpdatedEvent;
import com.ltadcrm.ltadcrm.gateway.mapper.ContactsMapper;
import com.ltadcrm.ltadcrm.gateway.mapper.CostCenterMapper;
import com.ltadcrm.ltadcrm.gateway.mapper.DetailsMapper;
import com.ltadcrm.ltadcrm.gateway.mapper.ItemsMapper;
import com.ltadcrm.ltadcrm.gateway.mapper.UsersMapper;
import com.ltadcrm.ltadcrm.repositories.ContactsRepository;
import com.ltadcrm.ltadcrm.repositories.CostCenterRepository;
import com.ltadcrm.ltadcrm.repositories.DetailsRepository;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdatedMethod {

        private final ContactsMapper contactsMapper;
        private final UsersMapper usersMapper;
        private final ItemsMapper itemsMapper;
        private final DetailsMapper detailsMapper;
        private final CostCenterMapper costCenterMapper;
        private final ItemsRepository itemsRepository;
        private final ContactsRepository contactsRepository;
        private final CostCenterRepository costCenterRepository;
        private final DetailsRepository detailsRepository;
        private final UsersRepository usersRepository;
        private final ApplicationEventPublisher eventPublisher;
      
    

        @Transactional

        public ResponseEntity<String> update(UpdateDTO updateDTO) {
                /*
                 * After update this line catch old value from Items -> details
                 */   Items oldObject= itemsRepository.findByIdWithPessimisticLock(updateDTO.getItemsDTO().getId()).get();
                        final String oldValueFromObject = oldObject.getDetails().getDescription();
                        
                try {

                        itemsRepository.save(itemsMapper.updateDomainFromDTO(
                                        itemsRepository.findByIdWithPessimisticLock(updateDTO.getItemsDTO().getId()).get(),
                                        updateDTO.getItemsDTO()));
                        usersRepository.save(usersMapper.updateDomainFromDTO(
                                        usersRepository.findByIdWithPessimisticLock(updateDTO.getContactsDTO().getId()).get(),
                                        updateDTO.getUsersDTO()));
                        detailsRepository.save(detailsMapper.updateDomainFromDTO(
                                        detailsRepository.findById(updateDTO.getDetailsDTO().getId()).get(),
                                        updateDTO.getDetailsDTO()));
                        contactsRepository.save(contactsMapper.updateDomainFromDTO(
                                        contactsRepository.findByIdWithPessimisticLock(updateDTO.getContactsDTO().getId()).get(),
                                        updateDTO.getContactsDTO()));
                        costCenterRepository.save(costCenterMapper.updateDomainFromDTO(
                                        costCenterRepository.findByIdWithPessimisticLock(updateDTO.getCostCenterDTO().getId()).get(),
                                        updateDTO.getCostCenterDTO()));

                        Items existingItem = itemsRepository.findByIdWithPessimisticLock(updateDTO.getItemsDTO().getId()).get();

                        /*
                        *Log register 
                        */
                        ItemUpdatedEvent event = new ItemUpdatedEvent(
                                        existingItem.getId(),
                                        existingItem.getLastModification(),
                                        existingItem.getValue(),
                                        existingItem.getDetails().getDescription(),
                                        oldValueFromObject);
                        eventPublisher.publishEvent(event);
                        
                        return ResponseEntity.ok("Dados salvos");

                } catch (Exception e) {
                        return ResponseEntity.badRequest().body("Error " + e);
                }
        }

}