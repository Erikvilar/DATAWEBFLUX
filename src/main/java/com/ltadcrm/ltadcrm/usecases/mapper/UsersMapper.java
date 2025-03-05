package com.ltadcrm.ltadcrm.usecases.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ltadcrm.ltadcrm.domain.Users;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UsersDTO;

@Mapper(componentModel = "spring")
public interface UsersMapper {
        Users updateDomainFromDTO(@MappingTarget Users users, UsersDTO usersDTO);
       
        @Mapping(source = "contacts", target = "contactsDTO")
        UsersDTO toDto(Users users);
}
