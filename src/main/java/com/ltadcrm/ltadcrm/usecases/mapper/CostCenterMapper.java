package com.ltadcrm.ltadcrm.usecases.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ltadcrm.ltadcrm.domain.CostCenter;
<<<<<<< HEAD:src/main/java/com/ltadcrm/ltadcrm/usecases/mapper/CostCenterMapper.java
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterDTO;
=======
>>>>>>> 088196398320d42ee1271c01106582a55410814a:src/main/java/com/ltadcrm/ltadcrm/gateway/mapper/CostCenterMapper.java

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterDTO;
@Mapper(componentModel = "spring")
public interface CostCenterMapper {
    CostCenter updateDomainFromDTO(@MappingTarget CostCenter costCenter, CostCenterDTO costCenterDTO);
}
