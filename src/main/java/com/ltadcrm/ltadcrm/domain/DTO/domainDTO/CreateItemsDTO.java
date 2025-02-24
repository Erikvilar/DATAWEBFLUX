package com.ltadcrm.ltadcrm.domain.DTO.domainDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemsDTO {

 
    private ItemsDTO itemsDTO;
    private UsersDTO usersDTO;
    private DetailsDTO detailsDTO;
    private long costCenterID;
    private ContactsDTO contactsDTO;
    private ReceivingDTO receivingDTO;
    private String userLogged;

    



}