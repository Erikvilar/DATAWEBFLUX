package com.ltadcrm.ltadcrm.usecases;




import java.util.List;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.Responsible;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ResponsibleDTO;
import com.ltadcrm.ltadcrm.repositories.ResponsibleRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterResponsibles {
    
    private final ResponsibleRepository repository;

    public List<ResponsibleDTO> getResponsible(){
        List<Responsible> responsible = repository.findAll();
        return responsible.stream().map(res -> {
             return new ResponsibleDTO(res.getResponsibleID(), res.getNameResponsible(), res.getOccupationResponsible(), res.getEmailResponsible(), res.getPhoneResponsible());
        }).toList();
        
    }
    public String delete(Long id){
        repository.deleteById(id);
        return "usuario deletado";
        
    }

}
