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

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity(name = "InventoryItems")
@Table(name = "tb_inventory_items")
public class InventoryItems {

    @Column(name = "id_inventory_items")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code_inventory_items")
    private String code;
    @Column(name = "especification_inventory_items")
    private String especification;
    @Column(name = "path_image_inventory_items")
    private String pathImage;
    @Column(name = "sde_inventory_items")
    private Long sde;
    @Column(name = "order_origin_inventory_items")
    private String order;
    @Column(name = "status_inventory_items")
    private String status;
    @Column(name = "nf_invoice_inventory_items")
    private String nfInvoice;
    @Column(name = "value_inventory_items")
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
