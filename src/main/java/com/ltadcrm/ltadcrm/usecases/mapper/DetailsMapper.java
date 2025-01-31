package com.ltadcrm.ltadcrm.usecases.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ltadcrm.ltadcrm.domain.Details;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.DetailsDTO;


@Mapper(componentModel = "spring")
public interface DetailsMapper {
    Details updateDomainFromDTO(@MappingTarget Details details, DetailsDTO detailsDTO);
}
