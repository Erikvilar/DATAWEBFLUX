package com.ltadcrm.ltadcrm.usecases;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.CostCenter;

import com.ltadcrm.ltadcrm.domain.Responsible;
import com.ltadcrm.ltadcrm.domain.Users;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ResponsibleDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UsersDTO;

import com.ltadcrm.ltadcrm.repositories.CostCenterRepository;
import com.ltadcrm.ltadcrm.repositories.ResponsibleRepository;
import com.ltadcrm.ltadcrm.repositories.UsersRepository;

import com.ltadcrm.ltadcrm.usecases.mapper.CostCenterMapper;
import com.ltadcrm.ltadcrm.usecases.mapper.ResponsibleMapper;
import com.ltadcrm.ltadcrm.usecases.mapper.UsersMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateMethod {


    private final UsersRepository usersRepository;
    private final CostCenterRepository costCenterRepository;

    private final ResponsibleRepository responsibleRepository;
    private final ResponsibleMapper responsibleMapper;


    private final UsersMapper usersMapper;
 
    private final CostCenterMapper costCenterMapper;


 
    public CostCenterDTO createCostCenter(CostCenterDTO costCenterDTO) {

        CostCenter costCenter = new CostCenter();
        costCenterMapper.updateDomainFromDTO(costCenter, costCenterDTO);
        return costCenterMapper.toDto(costCenterRepository.save(costCenter));

    }

    public UsersDTO createUsers(UsersDTO usersDTO) {

        Users users = new Users();
        usersMapper.updateDomainFromDTO(users, usersDTO);
        return usersMapper.toDto(usersRepository.save(users));

    }

    public ResponsibleDTO createResponsible(ResponsibleDTO responsibleDTO) throws Exception {
        Optional<Responsible> optional = responsibleRepository.findBynameResponsible(responsibleDTO.getNameResponsible());
        if(optional.isPresent()){
            throw new Exception("Usuaário já existe");
        }

        Responsible responsible = new Responsible();
        responsibleMapper.updateDomainFromDTO(responsible, responsibleDTO);
        return responsibleMapper.toDto(responsibleRepository.save(responsible));

    }

}
