package com.ltadcrm.ltadcrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Contacts;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    Optional<Contacts> findAllById(Long id);


    @Query(value = """
            select ct.* from tb_contact ct where ct.id_contact = :id
            """, nativeQuery= true)
    Optional<Contacts> findByIdWithPessimisticLock(@Param("id") Long id);
}
