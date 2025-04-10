package com.ltadcrm.ltadcrm.usecases.Logger;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String code;
    private String entityName;
    private Long entityId;
    private String fieldName;
    private String oldValue;
    private String newValue;
    private String type;
    private String userLog;
    private LocalDateTime timestamp;
    public AuditLog() {
        this.timestamp = LocalDateTime.now();
    }

  
}