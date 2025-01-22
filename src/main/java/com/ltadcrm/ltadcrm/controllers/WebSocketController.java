package com.ltadcrm.ltadcrm.controllers;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;



@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    public void notifyClientes(UpdateDTO updateRow){
        messagingTemplate.convertAndSend("/topic/updates", updateRow);
    }
   

}
