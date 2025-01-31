package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;


import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailDTO {

    // Dados de 'tb_users'
    @JsonProperty("id_usuario")
    private Long id;

    @JsonProperty("nome_usuario")
    private String userName;

    @JsonProperty("tipo_usuario")
    private String userType;

    // Dados de 'tb_items'
    @JsonProperty("id_item")
    private Long itemId;

    @JsonProperty("nf_invoice_item")
    private String nfInvoice;

    @JsonProperty("codigo_item")
    private String code;

    @JsonProperty("processoSEI")
    private String processSEI;

    @JsonProperty("observacao_item")
    private String observation;

    @JsonProperty("caminho_imagem_item")
    private String imagePath;

    @JsonProperty("pedido_origem")
    private String orderOrigin;

    @JsonProperty("sde_item")
    private Long sde;

    @JsonProperty("status_item")
    private String status;

    @JsonProperty("valor_item")
    private Double value;

    // Dados de 'tb_description'
    @JsonProperty("id_descricao")
    private Long descriptionId;

    @JsonProperty("marca_descricao")
    private String brand;

    @JsonProperty("descricao_item")
    private String description;

    @JsonProperty("localizacao_descricao")
    private String location;

    @JsonProperty("modelo_descricao")
    private String model;

    @JsonProperty("serie_descricao")
    private String series;

    // Dados de 'tb_cost_center'
    @JsonProperty("id_centro_custo")
    private Long costCenterId;

    @JsonProperty("nome_centro_custo")
    private String costCenterName;

    @JsonProperty("identificacao_centro_custo")
    private String costCenterIdentification;

    @JsonProperty("data_inicio_centro_custo")
    private Date costCenterStartDate;

    @JsonProperty("data_fim_centro_custo")
    private Date costCenterEndDate;

    // Dados de 'tb_contact'
    @JsonProperty("id_contato")
    private Long contactId;

    @JsonProperty("email_contato")
    private String contactEmail;

    @JsonProperty("responsavel_geral")
    private String generalResponsible;

    @JsonProperty("ocupacao_contato")
    private String contactOccupation;

    @JsonProperty("telefone_contato")
    private String contactPhone;

    @JsonProperty("lastModify")
    private String lastModification;


    //dados de ´´tb_receiving´
    @JsonProperty("id_recebimento")
    private Long receivingID;
    @JsonProperty("termo")
    private Long receivingCode;
    @JsonProperty("lotação")
    private String lotation;
    @JsonProperty("local")
    private String local;
    @JsonProperty("empSIAFI")
    private String empSiafi;
    
    @JsonProperty("updateIn")
    private LocalDateTime updateIn;

   

}