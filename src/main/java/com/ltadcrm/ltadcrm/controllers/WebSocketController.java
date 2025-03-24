package com.ltadcrm.ltadcrm.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyClientes(UpdateDTO updateRow) {
        messagingTemplate.convertAndSend("/topic/updates", updateRow);
    }

    @MessageMapping("/join")
    @SendTo("/topic/response")
    public ResponseEntity<UpdateDTO> join(@Payload UpdateDTO updateDTO, SimpMessageHeaderAccessor accessor) {
        return new ResponseEntity<>(updateDTO, HttpStatus.ACCEPTED);

    }

}
