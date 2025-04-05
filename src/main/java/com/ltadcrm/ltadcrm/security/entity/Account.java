package com.ltadcrm.ltadcrm.security.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ltadcrm.ltadcrm.security.entity.Enums.AccountRoles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "account")
@Entity(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Size(min = 5, message = "O nome do usuário deve conter pelo menos 6 caracteres.")
    private String login;
    @Size(min = 5, message = "A password deve conter pelo menos 5 caracteres.")
    private String password;

    private String avatar;

    private Boolean isLogged;

    private AccountRoles role;

    public Account(String login, String password, String avatar, Boolean isLogged, AccountRoles role) {
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.isLogged = isLogged;
        this.role = role;
        
        

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == AccountRoles.ADMIN)
            return List.of(
            new SimpleGrantedAuthority("ROLE_ADMIN"), 
            new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
