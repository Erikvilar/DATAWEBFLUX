package com.ltadcrm.ltadcrm.usecases.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


import com.ltadcrm.ltadcrm.domain.Receiving;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ReceivingDTO;
@Mapper(componentModel = "spring")
public interface ReceivingMapper {
        Receiving updateDomainFromDTO(@MappingTarget Receiving receiving, ReceivingDTO receivingDTO);
}
