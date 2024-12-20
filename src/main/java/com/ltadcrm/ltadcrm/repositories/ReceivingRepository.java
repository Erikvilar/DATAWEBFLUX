package com.ltadcrm.ltadcrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Receiving;



@Repository
public interface ReceivingRepository extends JpaRepository<Receiving, Long> {
 
    @Query(value = """
            select re.* from 
            tb_receiving 
            re where 
            re.receivingid = :id
            """, nativeQuery= true)
    Optional<Receiving> findByIdWithPessimisticLock(@Param("id") Long id);
}
