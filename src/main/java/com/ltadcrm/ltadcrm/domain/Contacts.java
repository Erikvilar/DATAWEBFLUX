package com.ltadcrm.ltadcrm.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Contacts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tb_contact")
public class Contacts implements Serializable {

    @Column(name = "id_contact")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email_contact")
    @JsonProperty("email")
    private String email;
    
    @Column(name = "phone_contact")
    private String phone;

    @Column(name="responsible_general")
    private String responsibleGeneral;


    @Column(name = "occupation_contact")
    private String occupation;

    @Column(name = "last_modify")
    private String lastModification;
    @Column(name = "update_in")
    
    private LocalDateTime updateIn;



}
