package com.ltadcrm.ltadcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.logs.Audit;
@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
  
}
