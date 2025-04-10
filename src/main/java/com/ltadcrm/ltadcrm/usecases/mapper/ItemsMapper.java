package com.ltadcrm.ltadcrm.usecases.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;

import com.ltadcrm.ltadcrm.domain.Items;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemsDTO;

@Mapper(componentModel = "spring")
public interface ItemsMapper {
    
    Items updateDomainFromDTO(@MappingTarget Items item, ItemsDTO dto);

    ItemsDTO toDto(Items items);
    Items toEntity(ItemsDTO itemsDTO);

}
