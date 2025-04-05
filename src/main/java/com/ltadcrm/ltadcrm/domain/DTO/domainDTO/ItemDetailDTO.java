package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailDTO {

    // Dados de 'tb_users'
    @JsonProperty("id_usuario")
    private Long userID;

    @JsonProperty("nome_usuario")
    private String userName;

    @JsonProperty("tipo_usuario")
    private String userType;

    @JsonProperty("email_usuario")
    private String emailUser;

    @JsonProperty("telefone_usuario")
    private String phoneUser;


    @JsonProperty("id_responsavel_geral")
    private Long responsibleID;

    @JsonProperty("nome_responsavel_geral")
    private String nameResponsible;

    @JsonProperty("ocupacao_responsavel")
    private String occupationResponsible;

    @JsonProperty("email_responsavel_geral")
    private String emailResponsible;

    @JsonProperty("telefone_responsavel_geral")
    private String phoneResponsible;

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
    private List<String> pathImage;

    @JsonProperty("pedido_origem")
    private String orderOrigin;

    @JsonProperty("sde_item")
    private Long sde;

    @JsonProperty("status_item")
    private String status;

    @JsonProperty("situacao_registro")
    private String situationRegister;

    @JsonProperty("valor_item")
    private Double value;

    @JsonProperty("lastModify")
    private List<String> lastModification;
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
    private Date  costCenterEndDate;

    // dados de ´´tb_receiving´
    @JsonProperty("id_recebimento")
    private Long receivingID;
    @JsonProperty("termo")
    private Long receivingCode;
    @JsonProperty("lotacao")
    private String lotation;
    @JsonProperty("fornecedor")
    private String supplier;
    @JsonProperty("email_fornecedor")
    private String email;
    @JsonProperty("termoPDF")
    private String pdfTerm;
    @JsonProperty("pedidoPDF")
    private String pdfOrder;
    @JsonProperty("pedido")
    private String orderCode;
    @JsonProperty("empSIAFI")
    private String empSiafi;

    @JsonProperty("updateIn")
    private LocalDateTime updateIn;

    public static ItemDetailDTO fromDto(UsersDTO usersDTO, ResponsibleDTO responsibleDTO, ItemsDTO itemsDTO,
            DetailsDTO detailsDTO, CostCenterDTO costCenterDTO, ReceivingDTO receivingDTO) {
        if (usersDTO == null || itemsDTO == null || detailsDTO == null || costCenterDTO == null
                || receivingDTO == null) {
            System.out.println("Algum dos DTOs está nulo");
        }

        return new ItemDetailDTO(
                // Dados de 'tb_users'
                usersDTO.getUserID(),
                usersDTO.getUserName(),
                usersDTO.getUserType(),
                usersDTO.getEmailUser(),
                usersDTO.getPhoneUser(),

                responsibleDTO.getResponsibleID(),
                responsibleDTO.getNameResponsible(),
                responsibleDTO.getOccupationResponsible(),
                responsibleDTO.getEmailResponsible(),
                responsibleDTO.getPhoneResponsible(),

                // Dados de 'tb_items'
                itemsDTO.getId(),
                itemsDTO.getNfInvoice(),
                itemsDTO.getNumber(),
                itemsDTO.getProcessSEI(),
                itemsDTO.getObservation(),
                itemsDTO.getPathImage(),
                itemsDTO.getOrder(),
                itemsDTO.getSde(),
                itemsDTO.getStatus(),
                itemsDTO.getSituationRegister(),
                itemsDTO.getValue(),
                itemsDTO.getLastModification(),
                // Dados de 'tb_description'
                detailsDTO.getId(),
                detailsDTO.getBrand(),
                detailsDTO.getDescription(),
                detailsDTO.getLocal(),
                detailsDTO.getModel(),
                detailsDTO.getSerial(),

                // Dados de 'tb_cost_center'
                costCenterDTO.getId(),
                costCenterDTO.getName(),
                costCenterDTO.getIdentification(),
                costCenterDTO.getInitialDate(),
                costCenterDTO.getEndDate(),

                // Dados de 'tb_contact'

                // Dados de 'tb_receiving'
                receivingDTO.getReceivingID(),
                receivingDTO.getReceivingCode(),
                receivingDTO.getLotation(),
                receivingDTO.getSupplier(),
                receivingDTO.getEmail(),
                receivingDTO.getPdfTerm(),
                receivingDTO.getPdfOrder(),
                receivingDTO.getOrderCode(),
                receivingDTO.getEmpSIAFI(),
                itemsDTO.getUpdateIn());
    }

}