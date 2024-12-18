package com.ltadcrm.ltadcrm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemsDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterByNameDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;
import com.ltadcrm.ltadcrm.gateway.CreateMethod;
import com.ltadcrm.ltadcrm.gateway.DeleteMethod;
import com.ltadcrm.ltadcrm.gateway.ReadMethod;
import com.ltadcrm.ltadcrm.gateway.UpdatedMethod;
import com.ltadcrm.ltadcrm.security.controller.authentication.RegisterDTO;

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
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("general")
@RestController
@RequiredArgsConstructor
public class GeneralController {

    private final ReadMethod readMethod;
    private final DeleteMethod deleteMethod;
    private final CreateMethod createMethod;
    private final UpdatedMethod updateMethod;

    @GetMapping
    public ResponseEntity<List<ItemDetailDTO>> showAllDTO() throws Exception {
        return new ResponseEntity<>(readMethod.list(), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<String> saveMethod(@RequestBody UpdateDTO updateDTO) {
        return createMethod.create(updateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") List<Long> id) {
        return deleteMethod.deleteItem(id);

    }

    @PutMapping("/update")
    public ResponseEntity<String> postMethodName(@RequestBody @Valid UpdateDTO updateDTO, RegisterDTO registerDTO) {
        return updateMethod.update(updateDTO);

    }
    @GetMapping("/costcenter/{name}")
    public ResponseEntity<List<CostCenterByNameDTO>> getMethodName(@PathVariable("name") String name) throws Exception {
        return new ResponseEntity<>(readMethod.readItemsByCostCenter(name), HttpStatus.OK);
    }
    
}
