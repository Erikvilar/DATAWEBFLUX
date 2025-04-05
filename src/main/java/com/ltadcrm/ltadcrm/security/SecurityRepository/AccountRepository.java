package com.ltadcrm.ltadcrm.security.SecurityRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.security.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
   UserDetails findByLogin(String login);
 
   Account findAccountByLogin(String login);

  
}
