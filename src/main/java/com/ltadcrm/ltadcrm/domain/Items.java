package com.ltadcrm.ltadcrm.domain;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Items")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_items")
@Data
public class Items implements Serializable{

   
    @Column(name = "id_items")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patrimony")
    private String number;
    @Lob
    @Column(name = "observation_item")
    private String observation;

    @Column(name = "pathImage_item")
    @ElementCollection
    @Size(max = 2)
    private List<String>pathImage;

    @Column(name = "sde_item")
    private Long sde;

    @Column(name="process_SEI")
    private String processSEI;

    @Column(name = "orderOrigin_item")
    private String order;

    @Column(name = "status_item")
    private String status;

    @Column(name = "situation_register")
    private String situationRegister;

    @Column(name = "nf_invoice_item")
    private String nfInvoice;

    @Column(name = "value_item")
    private Double value;

    @Column(name = "last_modify")
    @ElementCollection
    @Size(max = 2)
    private List<String> lastModification;

    @Column(name = "update_in")
    private LocalDateTime updateIn;
    /*
     * @Definindo relacionamentos para a a tabela items
     */
   
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_details")
    private Details details;

   
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_users")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_responsible")
    private Responsible responsible;
   
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_costCenter")
    private CostCenter costCenter;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receivingID")
    private Receiving receiving;


}
