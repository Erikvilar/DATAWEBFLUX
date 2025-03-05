package com.ltadcrm.ltadcrm.domain;



import java.io.Serializable;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    private Long id;
    @Column(name= "name_users")

    private String userName;
    @Column(name= "type_users")
    private String userType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact", referencedColumnName = "id_contact")
    private Contacts contacts;

}
