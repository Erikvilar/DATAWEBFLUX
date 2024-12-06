package com.ltadcrm.ltadcrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Details;
@Repository
public interface DetailsRepository extends JpaRepository<Details, Long>{
    
    Optional<Details> findAllById(Long id);
}
