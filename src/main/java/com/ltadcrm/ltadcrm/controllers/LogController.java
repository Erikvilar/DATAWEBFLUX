package com.ltadcrm.ltadcrm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltadcrm.ltadcrm.domain.logs.Audit;
import com.ltadcrm.ltadcrm.repositories.AuditRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("logs")
@RestController
public class LogController {
    

    @Autowired
    private AuditRepository auditRepository;

    @GetMapping("/")
    public ResponseEntity<List<Audit>> listLog() {
        return new ResponseEntity<>(auditRepository.findAll(), HttpStatus.OK);
    }
    
}
