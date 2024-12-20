package com.ltadcrm.ltadcrm.domain;

import java.time.LocalDateTime;


import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Items")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_items")
@Data
public class Items {

    @Column(name = "id_items")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code_item")
    private String number;
    @Column(name = "observation_item")
    private String observation;
    @Column(name = "pathImage_item")
    private String pathImage;
    @Column(name = "sde_item")
    private Long sde;
    @Column(name = "orderOrigin_item")
    private String order;
    @Column(name = "status_item")
    private String status;
    @Column(name = "nf_invoice_item")
    private String nfInvoice;
    @Column(name = "value_item")
    private Double value;
    @Column(name = "last_modify")
    private String lastModification;
    @Column(name = "update_in")
    @UpdateTimestamp
    private LocalDateTime updateIn;
    /*
     * @Definindo relacionamentos para a a tabela items(Patrimonios)
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_details")
    private Details details;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_users")
    private Users users;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_costCenter")
    private CostCenter costCenter;

}
