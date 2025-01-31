package com.ltadcrm.ltadcrm.usecases.strategy;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.usecases.interfaces.ItemDetailsDTOConvert;

import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;
@Service
public class ItemDetailsDTOConvertImpl implements ItemDetailsDTOConvert {

    @Override
    public ItemDetailDTO convert(Tuple tuple) {

        LocalDateTime updateIn = extractUpdateIn(tuple);

        return new ItemDetailDTO(
                tuple.get("id_usuario", Long.class),
                tuple.get("nome_usuario", String.class),
                tuple.get("tipo_usuario", String.class),
                tuple.get("id_item", Long.class),
                tuple.get("nf_invoice_item", String.class),
                tuple.get("codigo_item", String.class),
                tuple.get("processSEI", String.class),
                tuple.get("observacao_item", String.class),
                tuple.get("caminho_imagem_item", String.class),
                tuple.get("pedido_origem", String.class),
                tuple.get("sde_item", Long.class),
                tuple.get("status_item", String.class),
                tuple.get("valor_item", Double.class),
                tuple.get("id_descricao", Long.class),
                tuple.get("marca_descricao", String.class),
                tuple.get("descricao_item", String.class),
                tuple.get("localizacao_descricao", String.class),
                tuple.get("modelo_descricao", String.class),
                tuple.get("serie_descricao", String.class),
                tuple.get("id_centro_custo", Long.class),
                tuple.get("nome_centro_custo", String.class),
                tuple.get("identificacao_centro_custo", String.class),
                tuple.get("data_inicio_centro_custo", Date.class),
                tuple.get("data_fim_centro_custo", Date.class),
                tuple.get("id_contato", Long.class),
                tuple.get("email_contato", String.class),
                tuple.get("responsavel_geral", String.class),
                tuple.get("ocupacao_contato", String.class),
                tuple.get("telefone_contato", String.class),
                tuple.get("lastModify", String.class),
                tuple.get("id_recebimento", Long.class),
                tuple.get("termo", Long.class),
                tuple.get("lotação", String.class),
                tuple.get("local", String.class),
                tuple.get("empSIAFI", String.class),
                updateIn
        );
    }

    private LocalDateTime extractUpdateIn(Tuple tuple) {
        Object updateInObj = tuple.get("updateIn");
        if (updateInObj instanceof Timestamp updateInTimestamp) {
            return updateInTimestamp.toLocalDateTime();
        }
        return null;
    }
}
