package com.ltadcrm.ltadcrm.usecases.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ltadcrm.ltadcrm.domain.CostCenter;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterDTO;
@Mapper(componentModel = "spring")
public interface CostCenterMapper {
    CostCenter updateDomainFromDTO(@MappingTarget CostCenter costCenter, CostCenterDTO costCenterDTO);
    CostCenterDTO toDto(CostCenter costCenter);
}
