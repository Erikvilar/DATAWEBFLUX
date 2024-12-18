package com.ltadcrm.ltadcrm.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Details")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_details")
@Data
public class Details implements Serializable{
  
    @Column(name = "id_details")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "model_details")
    private String model;
    @Column(name = "brand_details")
    private String brand;
    @Column(name = "serial_details")
    private String serial;
    @Column(name = "details_details")
    private String description;
    @Column(name = "local_details")
    private String local;
     @Column(name = "last_modify")
    private String lastModification;
    @Column(name = "update_in")
    @UpdateTimestamp
    private LocalDateTime updateIn;





    

   
    

}
