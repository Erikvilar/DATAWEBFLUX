package com.ltadcrm.ltadcrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findAllById(Long id);

  
    @Query(value = """
            select u.* 
            from tb_users
             u where u.id_users = :id 
             """, nativeQuery= true)
    Optional<Users> findByIdWithPessimisticLock(@Param("id") Long id);
}
