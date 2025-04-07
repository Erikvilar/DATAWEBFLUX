package com.ltadcrm.ltadcrm.usecases;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ltadcrm.ltadcrm.domain.CostCenter;
import com.ltadcrm.ltadcrm.domain.Details;
import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.Receiving;
import com.ltadcrm.ltadcrm.domain.Responsible;
import com.ltadcrm.ltadcrm.domain.Users;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ReceivingListDTO;

import com.ltadcrm.ltadcrm.repositories.CostCenterRepository;
import com.ltadcrm.ltadcrm.repositories.DetailsRepository;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.repositories.ReceivingRepository;
import com.ltadcrm.ltadcrm.repositories.ResponsibleRepository;
import com.ltadcrm.ltadcrm.repositories.UsersRepository;
import com.ltadcrm.ltadcrm.usecases.Logger.AuditLog;
import com.ltadcrm.ltadcrm.usecases.Logger.AuditLogRepository;
import com.ltadcrm.ltadcrm.usecases.mapper.DetailsMapper;
import com.ltadcrm.ltadcrm.usecases.mapper.ItemsMapper;
import com.ltadcrm.ltadcrm.usecases.mapper.ReceivingMapper;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateListItems {

    private final ItemsRepository itemsRepository;
    private final UsersRepository usersRepository;
    private final CostCenterRepository costCenterRepository;
    private final DetailsRepository detailsRepository;
    private final ResponsibleRepository responsibleRepository;
    private final ReceivingRepository receivingRepository;
    private final ItemsMapper itemsMapper;

    private final DetailsMapper detailsMapper;
    private final AuditLogRepository logger;
    private final ReceivingMapper receivingMapper;

    @Transactional
    public ResponseEntity<String> create(ReceivingListDTO dto) throws Exception {

        try {

            Receiving receiving = new Receiving();
            receivingMapper.updateDomainFromDTO(receiving, dto.getReceivingDTO());

            final Receiving receivingSave = receivingRepository.save(receiving);

            List<Items> itemsList = dto.getItems().stream().map(itemsDTO -> {
                Items items = new Items();

                itemsMapper.updateDomainFromDTO(items, itemsDTO);

            
                    if (itemsRepository.findBynumber(items.getNumber()).isPresent()) {
                        throw new RuntimeException("Número patrimonial duplicado: " + items.getNumber());
                    }
                

                CostCenter costCenter = costCenterRepository.findById(itemsDTO.getCostCenterId())
                        .orElseThrow(() -> new RuntimeException("Não encontrei o id de centro de custo"));

                items.setCostCenter(costCenter);

                Users users = usersRepository.findById(itemsDTO.getUserId())
                        .orElseThrow(() -> new RuntimeException("Não encontrei o id do usuário"));
                items.setUsers(users);
                
               Responsible  responsible = responsibleRepository.findById(itemsDTO.getResponsibleId())
                        .orElseThrow(() -> new RuntimeException("Não encontrei o id do responsavel"));

                items.setResponsible(responsible);
                Details details = new Details();
                detailsMapper.updateDomainFromDTO(details, itemsDTO.getDetailsDTO());
                items.setDetails(detailsRepository.save(details));

                items.setReceiving(receivingSave);
                logger.save(new AuditLog(null, items.getNumber(),"",0L, "","",items.getDetails().getDescription(),"create",dto.getLogUser(), LocalDateTime.now()));
                return itemsRepository.save(items);
            }).toList();

            receiving.setItems(itemsList);

            return new ResponseEntity<>("Dados salvos", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
