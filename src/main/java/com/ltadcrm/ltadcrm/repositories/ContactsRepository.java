package com.ltadcrm.ltadcrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Contacts;


@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    
    Optional<Contacts> findAllById(Long id);
}
