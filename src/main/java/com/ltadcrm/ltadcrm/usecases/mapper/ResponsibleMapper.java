package com.ltadcrm.ltadcrm.usecases.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ltadcrm.ltadcrm.domain.Responsible;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ResponsibleDTO;

@Mapper(componentModel="spring")
public interface ResponsibleMapper {
    
    Responsible updateDomainFromDTO(@MappingTarget Responsible responsible, ResponsibleDTO responsibleDTO);
    ResponsibleDTO toDto(Responsible responsible);
}
