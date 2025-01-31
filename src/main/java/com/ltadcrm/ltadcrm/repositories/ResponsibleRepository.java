package com.ltadcrm.ltadcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Responsible;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
    
    Responsible findByName(String name);
}
