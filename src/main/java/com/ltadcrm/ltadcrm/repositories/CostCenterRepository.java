package com.ltadcrm.ltadcrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.CostCenter;
@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long>{
    
    Optional<CostCenter> findAllById(Long id);
}
