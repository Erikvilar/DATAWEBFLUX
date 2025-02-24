package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;


import java.time.LocalDateTime;
import java.util.Date;


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

    @JsonProperty("lastModify")
    private String lastModification;
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

    //dados de ´´tb_receiving´
    @JsonProperty("id_recebimento")
    private Long receivingID;
    @JsonProperty("termo")
    private Long receivingCode;
    @JsonProperty("lotação")
    private String lotation;
    @JsonProperty("fornecedor")
    private String supplier;
    @JsonProperty("email_fornecedor")
    private String email;
    @JsonProperty("termoPDF")
    private String pdfTerm;
    @JsonProperty("pedidoPDF")
    private String pdfOrder;
    @JsonProperty("empSIAFI")
    private String empSiafi;
    
    @JsonProperty("updateIn")
    private LocalDateTime updateIn;

    public static ItemDetailDTO fromDto(UsersDTO usersDTO, ItemsDTO itemsDTO, DetailsDTO detailsDTO, CostCenterDTO costCenterDTO, ContactsDTO contactsDTO, ReceivingDTO receivingDTO) {
        return new ItemDetailDTO(
            // Dados de 'tb_users'
            usersDTO.getId(),
            usersDTO.getUserName(),
            usersDTO.getUserType(),
    
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
            contactsDTO.getId(),
            contactsDTO.getEmail(),
            contactsDTO.getResponsibleGeneral(),
            contactsDTO.getOccupation(),
            contactsDTO.getPhone(),
    
            // Dados de 'tb_receiving'
            receivingDTO.getReceivingID(),
            receivingDTO.getReceivingCode(),
            receivingDTO.getLotation(),
            receivingDTO.getSupplier(),
            receivingDTO.getEmail(),
            receivingDTO.getPdfTerm(),
            receivingDTO.getPdfOrder(),
            receivingDTO.getEmpSIAFI(),
            itemsDTO.getUpdateIn()
        );
    }
   

}