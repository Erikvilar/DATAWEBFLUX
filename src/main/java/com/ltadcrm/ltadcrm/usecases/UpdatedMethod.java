
package com.ltadcrm.ltadcrm.usecases;

import org.springframework.context.ApplicationEventPublisher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltadcrm.ltadcrm.domain.Contacts;
import com.ltadcrm.ltadcrm.domain.Details;
import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.Receiving;
import com.ltadcrm.ltadcrm.domain.Users;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdatedMethod {

        private final ContactsMapper contactsMapper;
        private final UsersMapper usersMapper;
        private final ItemsMapper itemsMapper;
        private final DetailsMapper detailsMapper;
        private final CostCenterMapper costCenterMapper;
        private final ReceivingMapper receivingMapper;
        private final ItemsRepository itemsRepository;
        private final ContactsRepository contactsRepository;
        private final CostCenterRepository costCenterRepository;
        private final ReceivingRepository receivingRepository;
        private final DetailsRepository detailsRepository;
        private final UsersRepository usersRepository;
        private final ApplicationEventPublisher eventPublisher;

        @Transactional

        public UpdateDTO update(UpdateDTO updateDTO) throws Exception {
                /*
                 * After update this line catch old value from Items -> details
                 */ Items oldObject = itemsRepository.findByIdWithPessimisticLock(updateDTO.getItemsDTO().getId())
                                .get();
                final String oldValueFromObject = oldObject.getDetails().getDescription();

                try {
                        Items items = itemsRepository.findByIdWithPessimisticLock(updateDTO.getItemsDTO().getId())
                                        .get();
                        items.setCostCenter(costCenterRepository.findByName(updateDTO.getCostCenterDTO().getName())
                                        .orElseThrow(() -> new RuntimeException(
                                                        "ocorreu um erro ao buscar o projeto")));

                       Items itemsSaved =  itemsRepository.save(itemsMapper.updateDomainFromDTO(
                                        items,
                                        updateDTO.getItemsDTO()));

                       Users users = usersRepository.save(usersMapper.updateDomainFromDTO(
                                        usersRepository.findByIdWithPessimisticLock(updateDTO.getContactsDTO().getId())
                                                        .get(),
                                        updateDTO.getUsersDTO()));

                      Details details=  detailsRepository.save(detailsMapper.updateDomainFromDTO(
                                        detailsRepository.findById(updateDTO.getDetailsDTO().getId()).get(),
                                        updateDTO.getDetailsDTO()));

                       Contacts contacts = contactsRepository.save(contactsMapper.updateDomainFromDTO(
                                        contactsRepository
                                                        .findByIdWithPessimisticLock(updateDTO.getContactsDTO().getId())
                                                        .get(),
                                        updateDTO.getContactsDTO()));

                       Receiving receiving = receivingRepository.save(receivingMapper.updateDomainFromDTO(receivingRepository
                                        .findByIdWithPessimisticLock(updateDTO.getReceivingDTO().getReceivingID())
                                        .get(), updateDTO.getReceivingDTO()));

                        return new UpdateDTO(itemsMapper.toDto(itemsSaved),usersMapper.toDto(users), detailsMapper.toDto(details), costCenterMapper.toDto(itemsSaved.getCostCenter()), contactsMapper.toDto(contacts), receivingMapper.toDto(receiving) );

                } catch (Exception e) {
                        throw new Exception("ocorreu um erro ao atualizar os items" + e);
                }
        }

}