package com.ltadcrm.ltadcrm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ResponsibleDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterByNameDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;
import com.ltadcrm.ltadcrm.responses.ListWithTotalValues;
import com.ltadcrm.ltadcrm.usecases.CreateMethod;
import com.ltadcrm.ltadcrm.usecases.DeleteMethod;
import com.ltadcrm.ltadcrm.usecases.ReadMethod;
import com.ltadcrm.ltadcrm.usecases.RegisterResponsibles;
import com.ltadcrm.ltadcrm.usecases.UpdatedMethod;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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


@Slf4j
@RequestMapping("general")
@RestController
@RequiredArgsConstructor
public class GeneralController {

    private final ReadMethod readMethod;
    private final DeleteMethod deleteMethod;
    private final CreateMethod createMethod;
    private final UpdatedMethod updateMethod;
   private final RegisterResponsibles registerResponsibles;

    @GetMapping
    public ResponseEntity<List<ItemDetailDTO>> showAllDTO() throws Exception {
        return new ResponseEntity<>(readMethod.list(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveMethod(@RequestBody UpdateDTO updateDTO) {
        return createMethod.create(updateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable List<Long> id) {
        return deleteMethod.deleteItem(id);

    }

    @PutMapping("/update")
    public ResponseEntity<String> postMethodName(@RequestBody @Valid UpdateDTO updateDTO) {
        log.info("{}", updateDTO.getClass());
        return updateMethod.update(updateDTO);

    }

    @GetMapping("/costcenter/{name}")
    public ResponseEntity<ListWithTotalValues<CostCenterByNameDTO>> getMethodName(@PathVariable String name) throws Exception {
        return new ResponseEntity<>(readMethod.readItemsByCostCenter(name), HttpStatus.OK);
    }

    @PostMapping("/responsible")
    public ResponseEntity<ResponsibleDTO> registerResponsible(@RequestBody ResponsibleDTO responsibleDTO) throws Exception{
        return new ResponseEntity<>(registerResponsibles.register(responsibleDTO), HttpStatus.OK);
    }
    @GetMapping("/responsible")
    public ResponseEntity<List<ResponsibleDTO>> getAllResponsibles() {
        return new ResponseEntity<>(registerResponsibles.getResponsible(), HttpStatus.OK);
    }
    @DeleteMapping("/responsible/{id}")
    public ResponseEntity<String> deleteSomeResponsible(@PathVariable Long id){
       return  new ResponseEntity<>(registerResponsibles.delete(id), HttpStatus.OK);

    }
    

}
