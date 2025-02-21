package com.ltadcrm.ltadcrm.usecases.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemsDTO;
@Mapper(componentModel = "spring", uses = {CostCenterMapper.class, DetailsMapper.class, ReceivingMapper.class, UsersMapper.class})
public interface ItemsMapper {
    @Mapping(target = "costCenter", source = "costCenterDTO")
    @Mapping(target = "details", source = "detailsDTO")
    @Mapping(target = "receiving", source = "receivingDTO")
    @Mapping(target = "users", source = "usersDTO")
    Items updateDomainFromDTO(@MappingTarget Items item, ItemsDTO dto);
    @Mapping(target = "costCenter", source = "costCenter")
    @Mapping(target = "details", source = "details")
    @Mapping(target = "receiving", source = "receiving")
    @Mapping(target = "users", source = "users")
    List<ItemsDTO> toDTOList(List<Items> items);


}
