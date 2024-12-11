package com.ltadcrm.ltadcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Receiving;

@Repository
public interface ReceivingRepository extends JpaRepository<Receiving, Long>{
    
}
