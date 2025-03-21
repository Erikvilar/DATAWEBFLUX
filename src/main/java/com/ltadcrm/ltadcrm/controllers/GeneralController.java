package com.ltadcrm.ltadcrm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ItemDetailDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ReceivingListDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.ResponsibleDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterByNameDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.CostCenterDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;
import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UsersDTO;
import com.ltadcrm.ltadcrm.responses.ListWithTotalValues;
import com.ltadcrm.ltadcrm.usecases.CreateListItems;
import com.ltadcrm.ltadcrm.usecases.CreateMethod;
import com.ltadcrm.ltadcrm.usecases.DeleteMethod;
import com.ltadcrm.ltadcrm.usecases.ReadMethod;
import com.ltadcrm.ltadcrm.usecases.RegisterResponsibles;
import com.ltadcrm.ltadcrm.usecases.UpdatedMethod;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.ACCEPTED;

import static org.springframework.http.HttpStatus.OK;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@RequestMapping("general")
@RestController
@RequiredArgsConstructor
public class GeneralController {

    private final ReadMethod readMethod;
    private final DeleteMethod deleteMethod;
    private final CreateMethod createMethod;
    private final CreateListItems createListItems;
    private final UpdatedMethod updateMethod;
    private final RegisterResponsibles registerResponsibles;
    

    @ResponseStatus(OK)
    @GetMapping
    public List<ItemDetailDTO> showAllDTO() throws Exception {
        return readMethod.list();
    }

    @ResponseStatus(OK)
    @GetMapping("/costCenter")
    public List<CostCenterDTO> costCenterDTOs() throws Exception {
        return readMethod.getCostCenterDTOs();
    }

    @ResponseStatus(OK)
    @GetMapping("/users")
    public List<UsersDTO> usersDtos() throws Exception {
        return readMethod.getUsersDTOs();
    }
 

    @ResponseStatus(CREATED)
    @PostMapping("/create/costcenter")
    public CostCenterDTO createCostCenter(@RequestBody CostCenterDTO costCenterDTO) {
        return createMethod.createCostCenter(costCenterDTO);
    }

    
    @PostMapping("/create/list")
    public ResponseEntity<String> createListItems(@RequestBody ReceivingListDTO dto) throws Exception {
        log.info("Requisição ocorreu");
        return createListItems.create(dto);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable List<Long> id) {
        return deleteMethod.deleteItem(id);

    }

    @ResponseStatus(ACCEPTED)
    @PutMapping("/update")
    public UpdateDTO postMethodName(@RequestBody @Valid UpdateDTO updateDTO) throws Exception{
        return updateMethod.update(updateDTO);

    }

    @ResponseStatus(OK)
    @GetMapping("/costcenter/{name}")
    public ListWithTotalValues<CostCenterByNameDTO> getMethodName(@PathVariable String name) throws Exception {
        return readMethod.readItemsByCostCenter(name);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/responsible")
    public ResponsibleDTO registerResponsible(@RequestBody ResponsibleDTO responsibleDTO) throws Exception {
        return createMethod.createResponsible(responsibleDTO);
    }

    @ResponseStatus(CREATED)
    @PostMapping("create/users")
    public UsersDTO registerUsers(@RequestBody UsersDTO usersDTO) throws Exception {
        return createMethod.createUsers(usersDTO);
    }

    @ResponseStatus(OK)
    @GetMapping("/responsible")
    public List<ResponsibleDTO> getAllResponsibles() {
        return registerResponsibles.getResponsible();
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/responsible/{id}")
    public String deleteSomeResponsible(@PathVariable Long id) {
        return registerResponsibles.delete(id);

    }

}
