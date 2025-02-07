package com.ltadcrm.ltadcrm.usecases;




import java.util.List;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.Responsible;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ResponsibleDTO;
import com.ltadcrm.ltadcrm.repositories.ResponsibleRepository;
import com.ltadcrm.ltadcrm.usecases.mapper.ResponsibleMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterResponsibles {
    
    private final ResponsibleRepository repository;
    private final ResponsibleMapper responsibleMapper;


    public ResponsibleDTO register(ResponsibleDTO responsibleDTO) throws Exception {
     
        if(repository.findByName(responsibleDTO.getName()) != null){
            throw new Exception("Usuario ja existe");
        }
        Responsible responsible = new Responsible();
        responsibleMapper.updateDomainFromDTO(responsible, responsibleDTO);
        repository.save(responsible);
        
        return responsibleMapper.toDto(responsible);
    
    }

    public List<ResponsibleDTO> getResponsible(){
        List<Responsible> responsible = repository.findAll();
        return responsible.stream().map(res -> {
             return new ResponsibleDTO(res.getId(), res.getName(), res.getOccupation());
        }).toList();
        
    }
    public String delete(Long id){
        repository.deleteById(id);
        return "usuario deletado";
        
    }

}
