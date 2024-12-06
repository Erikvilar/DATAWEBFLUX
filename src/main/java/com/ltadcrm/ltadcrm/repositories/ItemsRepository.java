package com.ltadcrm.ltadcrm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ltadcrm.ltadcrm.domain.InventoryItems;
import com.ltadcrm.ltadcrm.domain.dto.domainDTO.ItemDetailDTO;

@Repository
public interface ItemsRepository extends JpaRepository<InventoryItems, Long> {
  

    @Query(value = """
        SELECT 
            u.id_users,
            u.name_users,
            u.type_users,

            i.id_inventory_items,
            i.nf_invoice_inventory_items,
            i.code_inventory_items,
            i.especification_inventory_items,
            i.path_image_inventory_items,
            i.order_origin_inventory_items,
            i.sde_inventory_items,
            i.status_inventory_items,
            i.value_inventory_items,
            i.last_modify,
            i.update_in,

            d.id_details,
            d.brand_details,
            d.details_details,
            d.local_details,
            d.model_details,
            d.serial_details,

            c.id_cost_center,
            c.end_date_cost_center,
            c.identification_cost_center,
            c.initial_date_cost_center,
            c.name_cost_center,

            co.id_contact,
            co.email_contact,
            co.occupation_contact,
            co.phone_contact
        FROM 
            tb_inventory_items i
        JOIN 
            tb_users u ON i.id_users = u.id_users
        JOIN 
            tb_details d ON i.id_details = d.id_details
        JOIN 
            tb_cost_center c ON i.id_cost_center = c.id_cost_center
        JOIN 
            tb_contact co ON co.id_contact = i.id_users
        """, nativeQuery = true)
    List<InventoryItems>findAllInventoryItems();

    Optional<InventoryItems> findAllById(Long id);
    

}
