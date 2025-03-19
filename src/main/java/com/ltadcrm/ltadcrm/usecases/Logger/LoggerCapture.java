package com.ltadcrm.ltadcrm.usecases.Logger;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ltadcrm.ltadcrm.domain.Contacts;
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

    public void captureItems(Items newValues, Items oldValues, String userLog) throws Exception {

        if (newValues == null || oldValues == null) {

            return;
        }

        Long entityId = newValues.getId();
        String entityName = newValues.getClass().getSimpleName();

        compare(entityName, "Observation", oldValues.getObservation(), newValues.getObservation(), entityId, userLog);
        compare(entityName, "SDE", oldValues.getSde().toString(), newValues.getSde().toString(), entityId, userLog);
        compare(entityName, "Process SEI", oldValues.getProcessSEI(), newValues.getProcessSEI(), entityId, userLog);
        compare(entityName, "Order Origin", oldValues.getOrder(), newValues.getOrder(), entityId, userLog);
        compare(entityName, "Status", oldValues.getStatus(), newValues.getStatus(), entityId, userLog);
        compare(entityName, "Situation Register", oldValues.getSituationRegister(),
                newValues.getSituationRegister(), entityId, userLog);
        compare(entityName, "NF Invoice", oldValues.getNfInvoice(), newValues.getNfInvoice(), entityId, userLog);
        compare(entityName, "Value", oldValues.getValue().toString(), newValues.getValue().toString(), entityId,
                userLog);

    }

    public void captureDetails(Details newValues, Details oldValues, String userLog) {
        if (newValues == null || oldValues == null) {
            return; // Evita erro caso algum objeto seja nulo
        }
        Long entityId = newValues.getId();

        String entityName = newValues.getClass().getSimpleName();

        compare(entityName, "Model", oldValues.getModel(), newValues.getModel(), entityId, userLog);
        compare(entityName, "Brand", oldValues.getBrand(), newValues.getBrand(), entityId, userLog);
        compare(entityName, "Serial", oldValues.getSerial(), newValues.getSerial(), entityId, userLog);
        compare(entityName, "Description", oldValues.getDescription(), newValues.getDescription(), entityId, userLog);
        compare(entityName, "Local", oldValues.getLocal(), newValues.getLocal(), entityId, userLog);
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

    public void captureContacts(Contacts newValues, Contacts oldValues) {
        if (newValues == null || oldValues == null) {
            return; // Evita erro caso algum objeto seja nulo
        }
    }

    private void compare(String entityName, String fieldName, String oldValue, String newValue, Long entityId,
            String userLog) {
        if (!Objects.equals(oldValue, newValue)) {
            log.info("Alteração detectada: Entidade={}, Campo={}, De='{}' Para='{}'", entityName, fieldName, oldValue,
                    newValue);
            auditService.logChange(entityName, entityId, fieldName, oldValue, newValue, userLog);
        } else {
            log.debug("Nenhuma alteração no campo {} da entidade {}", fieldName, entityName);
        }
    }
}
