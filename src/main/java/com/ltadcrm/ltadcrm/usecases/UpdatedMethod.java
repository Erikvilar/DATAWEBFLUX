
package com.ltadcrm.ltadcrm.usecases;

import org.springframework.context.ApplicationEventPublisher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.ltadcrm.ltadcrm.repositories.ReceivingRepository;
import com.ltadcrm.ltadcrm.repositories.UsersRepository;
import com.ltadcrm.ltadcrm.usecases.Logger.LoggerCapture;
import com.ltadcrm.ltadcrm.usecases.mapper.*;
import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdatedMethod {

        private final ItemsMapperImpl itemsMapperImpl;

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
        private final LoggerCapture loggerCapture;

        @Transactional
        public UpdateDTO update(UpdateDTO updateDTO) throws Exception {

                try {
                        Items itemsOld = itemsRepository.findByIdWithPessimisticLock(updateDTO.getItemsDTO().getId())
                                        .orElseThrow(() -> new RuntimeException(
                                                        "ocorreu um erro ao buscar o item"));

                        Details detailsOld = detailsRepository.findById(updateDTO.getDetailsDTO().getId())
                                        .orElseThrow(() -> new RuntimeException(
                                                        "Não foi encontrado o ID de Details"));

                        Contacts contactsOld = contactsRepository
                                        .findByIdWithPessimisticLock(updateDTO.getContactsDTO().getId())
                                        .orElseThrow(() -> new RuntimeException(
                                                        "Não foi encontrado o ID de contatos"));

                        CostCenter costCenterOld = costCenterRepository
                                        .findByName(updateDTO.getCostCenterDTO().getName())  .orElseThrow(() -> new RuntimeException(
                                                "Não foi encontrado o ID de projetos"));

                        loggerCapture.captureItems(itemsMapper.toEntity(updateDTO.getItemsDTO()), itemsOld,
                                        updateDTO.getItemsDTO().getLastModification().get(0));

                        loggerCapture.captureDetails(detailsMapper.toEntity(updateDTO.getDetailsDTO()), detailsOld,
                                        updateDTO.getItemsDTO().getLastModification().get(0));


                        itemsOld.setCostCenter(costCenterOld);

                        itemsOld.setUsers(usersRepository.findByUserName(updateDTO.getUsersDTO().getUserName())
                                        .orElseThrow(() -> new RuntimeException(
                                                        "ocorreu um erro ao buscar o usuario")));

                        Items itemsSaved = itemsRepository
                                        .save(itemsMapper.updateDomainFromDTO(itemsOld, updateDTO.getItemsDTO()));

                        Details detailsSaved = detailsRepository
                                        .save(detailsMapper.updateDomainFromDTO(detailsOld, updateDTO.getDetailsDTO()));

                        Contacts contactsSaved = contactsRepository
                                        .save(contactsMapper.updateDomainFromDTO(contactsOld,
                                                        updateDTO.getContactsDTO()));

                        Receiving receiving = receivingRepository.save(receivingMapper.updateDomainFromDTO(receivingRepository.findByIdWithPessimisticLock( updateDTO.getReceivingDTO().getReceivingID())
                                                        .orElseThrow(() -> new RuntimeException(
                                                                        "Não foi encontrado o ID de receiving")),
                                                        updateDTO.getReceivingDTO()));

                        return new UpdateDTO(itemsMapper.toDto(itemsSaved), usersMapper.toDto(itemsSaved.getUsers()),
                                        detailsMapper.toDto(detailsSaved),
                                        costCenterMapper.toDto(itemsSaved.getCostCenter()),
                                        contactsMapper.toDto(contactsSaved),
                                        receivingMapper.toDto(receiving));

                } catch (Exception e) {
                        throw new Exception("ocorreu um erro ao atualizar os items" + e);
                }
        }

}