package com.ltadcrm.ltadcrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.Users;
@Repository
public interface UsersRepository  extends JpaRepository<Users, Long>{
    
    Optional<Users> findAllById(Long id);
}
