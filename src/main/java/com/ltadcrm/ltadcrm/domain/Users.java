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

@Entity(name="Users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="tb_users")
public class Users implements Serializable{

    @Column(name= "id_users")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name= "name_users")
    private String userName;

    @Column(name= "type_users")
    private String userType;
    
    @Column(name= "email_user")
    private String emailUser;

    @Column(name= "phone_user")
    private String phoneUser;


}
