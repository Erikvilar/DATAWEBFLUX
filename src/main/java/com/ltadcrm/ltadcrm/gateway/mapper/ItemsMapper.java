package com.ltadcrm.ltadcrm.gateway.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;


import com.ltadcrm.ltadcrm.domain.InventoryItems;
import com.ltadcrm.ltadcrm.domain.dto.domainDTO.ItemsDTO;
@Mapper(componentModel = "spring")
public interface ItemsMapper {

    InventoryItems updateDomainFromDTO(@MappingTarget InventoryItems item, ItemsDTO dto);


}
