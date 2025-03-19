package com.ltadcrm.ltadcrm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Items;

import jakarta.persistence.Tuple;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {

    @Query(value = "CALL GetAllItems()", nativeQuery = true)
    List<Tuple> findAllItemsDTOs();

    Optional<Items> findAllById(Long id);

    List<Items> findByCostCenterName(String name);


    @Query(value = """
            select it.*
            from tb_items
            it where
            it.id_items = :id
            """, nativeQuery= true)
    Optional<Items> findByIdWithPessimisticLock(@Param("id") Long id);



    Optional<Items> findBynumber(String number);

}
