package com.ltadcrm.ltadcrm.gateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemsDTO;
@Mapper(componentModel = "spring")
public interface ItemsMapper {



    Items updateDomainFromDTO(@MappingTarget Items item, ItemsDTO dto);
    Items toDomain(ItemsDTO itemsDTO);


}
