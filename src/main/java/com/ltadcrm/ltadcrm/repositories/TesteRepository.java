package com.ltadcrm.ltadcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Teste;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long> {
    
}
