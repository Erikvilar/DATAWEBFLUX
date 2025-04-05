package com.ltadcrm.ltadcrm.usecases.Logger;

import java.util.Objects;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;


import com.ltadcrm.ltadcrm.domain.CostCenter;
import com.ltadcrm.ltadcrm.domain.Details;
import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.Users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoggerCapture {

    private final AuditService auditService;

    public void captureItems(String code, Items newValues, Items oldValues, String type, String userLog) throws Exception {

        if (newValues == null || oldValues == null) {

            return;
        }
        
        Long entityId = newValues.getId();
        String entityName = newValues.getClass().getSimpleName();

        compare(code, entityName, "Observação", oldValues.getObservation(), newValues.getObservation(),  entityId, type, userLog);
        compare(code, entityName, "SDE", oldValues.getSde().toString(), newValues.getSde().toString(),  entityId, type, userLog);
        compare(code, entityName, "Processo SEI", oldValues.getProcessSEI(), newValues.getProcessSEI(),  entityId, type, userLog);
        compare(code, entityName, "Pedido de origem", oldValues.getOrder(), newValues.getOrder(),  entityId, type, userLog);
        compare(code, entityName, "Status", oldValues.getStatus(), newValues.getStatus(),  entityId, type, userLog);
        compare(code, entityName, "Situação de registro", oldValues.getSituationRegister(),
                newValues.getSituationRegister(),  entityId, type, userLog);
        compare(code, entityName, "NF Invoice", oldValues.getNfInvoice(), newValues.getNfInvoice(),  entityId, type, userLog);
        compare(code, entityName, "Valor do item", oldValues.getValue().toString(), newValues.getValue().toString(), entityId,type,
                userLog);

    }

    public void captureDetails(String code, Details newValues, Details oldValues, String type, String userLog) {
        if (newValues == null || oldValues == null) {
            return; // Evita erro caso algum objeto seja nulo
        }
        Long entityId = newValues.getId();

        String entityName = newValues.getClass().getSimpleName();

        compare(code, entityName, "Modelo", oldValues.getModel(), newValues.getModel(),  entityId, type, userLog);
        compare(code, entityName, "Marca", oldValues.getBrand(), newValues.getBrand(),  entityId, type, userLog);
        compare(code, entityName, "Serial", oldValues.getSerial(), newValues.getSerial(),  entityId, type, userLog);
        compare(code, entityName, "Descrição", oldValues.getDescription(), newValues.getDescription(),  entityId, type, userLog);
        compare(code, entityName, "Local", oldValues.getLocal(), newValues.getLocal(),  entityId, type, userLog);
    }

    public void captureUsers(String code, Users newValues, Users oldValues, String type, String userLog) {
        if (newValues == null || oldValues == null) {
            return; // Evita erro caso algum objeto seja nulo
        }
        Long entityId = newValues.getUserID();
        String entityName = newValues.getClass().getSimpleName();
        compare(code, entityName, "Nome do imediato", oldValues.getUserName(), newValues.getUserName(),  entityId, type, userLog);
        
    }

    public void captureCostCenter(String code, CostCenter newValues, CostCenter oldValues, String type, String userLog) {
        if (newValues == null || oldValues == null) {
            return; // Evita erro caso algum objeto seja nulo
        }
        Long entityId = newValues.getId();
        String entityName = newValues.getClass().getSimpleName();
        compare(code, entityName, "sigla", oldValues.getName(), newValues.getName(),  entityId, type, userLog);
    }

  

    private void compare(String code, String entityName, String fieldName, String oldValue, String newValue, Long entityId,String type,
            String userLog) {
        if (!Objects.equals(oldValue, newValue)) {
            log.info("Alteração detectada: Entidade={}, Campo={}, De='{}' Para='{}'", entityName, fieldName, oldValue,
                    newValue);
            auditService.logChange(code, entityName, entityId, fieldName, oldValue, newValue,type, userLog);
        } else {
            log.debug("Nenhuma alteração no campo {} da entidade {}", fieldName, entityName);
        }
    }
}
