package com.ltadcrm.ltadcrm.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltadcrm.ltadcrm.usecases.Logger.AuditLog;
import com.ltadcrm.ltadcrm.usecases.Logger.AuditLogRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.OK;



@Slf4j
@RequestMapping("log")
@RestController
@RequiredArgsConstructor
public class LogController {
    
       private final AuditLogRepository repository;

       @ResponseStatus(OK)
       @GetMapping
       public List<AuditLog> showAllLogs (){
       Pageable pageable = PageRequest.of(0, 100, Sort.by(Sort.Direction.ASC, "id"));
        return repository.findAll(pageable).getContent();
       }


}
