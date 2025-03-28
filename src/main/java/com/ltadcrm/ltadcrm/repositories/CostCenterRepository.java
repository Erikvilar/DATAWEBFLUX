package com.ltadcrm.ltadcrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.CostCenter;



@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {

    Optional<CostCenter> findAllById(Long id);


    @Query(value = """
            select co.* from
            tb_cost_center co where
            co.id_cost_center = :id""", nativeQuery= true)
    Optional<CostCenter> findByIdWithPessimisticLock(@Param("id") Long id);


    Optional<CostCenter> findByName(String name);
}
