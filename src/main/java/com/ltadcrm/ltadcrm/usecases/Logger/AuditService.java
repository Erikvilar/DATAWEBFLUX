package com.ltadcrm.ltadcrm.usecases.Logger;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;
  

    public void logChange(String code,String entityName, Long entityId, String fieldName, String oldValue, String newValue,String type, String userLog) {

        AuditLog log = new AuditLog();
        log.setUserLog(userLog);
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setCode(code);
        log.setFieldName(fieldName);
        log.setOldValue(oldValue);
        log.setNewValue(newValue);
        log.setType(type);
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
      

    }
}