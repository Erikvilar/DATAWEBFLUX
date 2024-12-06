package com.ltadcrm.ltadcrm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltadcrm.ltadcrm.domain.DTO.authentication.RegisterDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;
import com.ltadcrm.ltadcrm.gateway.CreateAllIEntities;
import com.ltadcrm.ltadcrm.gateway.DeleteAllEntities;
import com.ltadcrm.ltadcrm.gateway.FindAllEntities;
import com.ltadcrm.ltadcrm.gateway.UpdateAllEntities;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("general")
@RestController
@RequiredArgsConstructor
public class GeneralController {

    private final FindAllEntities findAllEntities;
    private final DeleteAllEntities deleteAllEntities;
    private final CreateAllIEntities createAllIEntities;
    private final UpdateAllEntities updateAllEntities;

    @GetMapping
    public ResponseEntity<List<ItemDetailDTO>> showAllDTO() throws Exception {
        return new ResponseEntity<>(findAllEntities.list(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveMethod(@RequestBody UpdateDTO updateDTO) {
        return createAllIEntities.create(updateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") List<Long> id) {
        return deleteAllEntities.deleteItem(id);

    }

    @PutMapping("/update")
    public ResponseEntity<String> postMethodName(@RequestBody @Valid UpdateDTO updateDTO, RegisterDTO registerDTO) {
        return updateAllEntities.update(updateDTO);

    }
}
