package com.ltadcrm.ltadcrm.domain;



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
public class Responsible {
    

   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String occupation;





}
