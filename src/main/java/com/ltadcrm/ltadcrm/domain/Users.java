package com.ltadcrm.ltadcrm.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="tb_users")
public class Users {

    @Column(name= "id_users")
    @JsonProperty("id_usuario")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "name_users")
    @JsonProperty("usuario")
    private String userName;
    @Column(name= "type_users")
    @JsonProperty("tipo")
    private String userType;
    @Column(name = "last_modify")
    private String lastModification;
    @Column(name = "update_in")
    @UpdateTimestamp
    private LocalDateTime updateIn;
}
