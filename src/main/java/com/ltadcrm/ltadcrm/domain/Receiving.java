package com.ltadcrm.ltadcrm.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="Receiving")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_receiving")
public class Receiving implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receivingID;
    private Long receivingCode;
    private String lotation;
    private String supplier;
    private String email;
    private String pdfTerm;
    private String pdfOrder;
    private String empSIAFI;



}
