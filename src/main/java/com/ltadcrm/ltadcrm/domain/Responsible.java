package com.ltadcrm.ltadcrm.domain;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="Responsible")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_responsible")
public class Responsible implements Serializable {
    

    @Column(name= "id_responsible")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responsibleID;

    @Column(name="responsible_name")
    private String nameResponsible; 

    @Column(name= "ocuppation_responsible")
    private String occupationResponsible;
    
    @Column(name= "email_user")
    private String emailResponsible;

    @Column(name= "phone_user")
    private String phoneResponsible;

}
