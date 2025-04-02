package com.ltadcrm.ltadcrm.usecases.Logger;

import java.util.Objects;

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

        compare(code, entityName, "Observation", oldValues.getObservation(), newValues.getObservation(),  entityId, type, userLog);
        compare(code, entityName, "SDE", oldValues.getSde().toString(), newValues.getSde().toString(),  entityId, type, userLog);
        compare(code, entityName, "Process SEI", oldValues.getProcessSEI(), newValues.getProcessSEI(),  entityId, type, userLog);
        compare(code, entityName, "Order Origin", oldValues.getOrder(), newValues.getOrder(),  entityId, type, userLog);
        compare(code, entityName, "Status", oldValues.getStatus(), newValues.getStatus(),  entityId, type, userLog);
        compare(code, entityName, "Situation Register", oldValues.getSituationRegister(),
                newValues.getSituationRegister(),  entityId, type, userLog);
        compare(code, entityName, "NF Invoice", oldValues.getNfInvoice(), newValues.getNfInvoice(),  entityId, type, userLog);
        compare(code, entityName, "Value", oldValues.getValue().toString(), newValues.getValue().toString(), entityId,type,
                userLog);

    }

    public void captureDetails(String code, Details newValues, Details oldValues, String type, String userLog) {
        if (newValues == null || oldValues == null) {
            return; // Evita erro caso algum objeto seja nulo
        }
        Long entityId = newValues.getId();

        String entityName = newValues.getClass().getSimpleName();

        compare(code, entityName, "Model", oldValues.getModel(), newValues.getModel(),  entityId, type, userLog);
        compare(code, entityName, "Brand", oldValues.getBrand(), newValues.getBrand(),  entityId, type, userLog);
        compare(code, entityName, "Serial", oldValues.getSerial(), newValues.getSerial(),  entityId, type, userLog);
        compare(code, entityName, "Description", oldValues.getDescription(), newValues.getDescription(),  entityId, type, userLog);
        compare(code, entityName, "Local", oldValues.getLocal(), newValues.getLocal(),  entityId, type, userLog);
    }

    public void captureUsers(Users newValues, Users oldValues) {
        if (newValues == null || oldValues == null) {
            return; // Evita erro caso algum objeto seja nulo
        }
    }

    public void captureCostCenter(CostCenter newValues, CostCenter oldValues) {
        if (newValues == null || oldValues == null) {
            return; // Evita erro caso algum objeto seja nulo
        }
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
