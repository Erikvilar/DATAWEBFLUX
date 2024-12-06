package com.ltadcrm.ltadcrm.domain.logs;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "audit_log")

public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_item;
    @JsonProperty("Nome antigo")
    private String name_item_old;
    @JsonProperty("Nome novo")
    private String name_item_new;
    private String email_user;
    private Date create_in;
    private Date update_in;
    private String type_action;

}
