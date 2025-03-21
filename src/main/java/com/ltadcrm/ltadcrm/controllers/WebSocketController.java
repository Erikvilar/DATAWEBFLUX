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
import org.springframework.web.socket.WebSocketSession;

import com.ltadcrm.ltadcrm.domain.DTO.domainDTO.UpdateDTO;
import com.ltadcrm.ltadcrm.security.service.SecurityServiceControll;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final SecurityServiceControll security;

    public void notifyClientes(UpdateDTO updateRow) {
        messagingTemplate.convertAndSend("/topic/updates", updateRow);
    }

    @MessageMapping("/join")
    @SendTo("/topic/response")
    public ResponseEntity<UpdateDTO> join(@Payload UpdateDTO updateDTO, SimpMessageHeaderAccessor accessor) {
        return new ResponseEntity<>(updateDTO, HttpStatus.ACCEPTED);

    }

    public void onSessionClose(WebSocketSession session) {
        String userLogin = getUserLoginFromSession(session);
        try {
            log.info("Sessão WebSocket finalizada para {}", userLogin);
            log.info("Verificando a sessão WebSocket: {}", session.getId());
            security.logout(userLogin);
        } catch (Exception e) {
            log.error("Erro ao tentar deslogar usuário após finalização da sessão WebSocket", e);
        }
    }

    private String getUserLoginFromSession(WebSocketSession session) {
        log.info("my session WEBSOCKET {}", session.getId());
        return session.getAttributes().get("userLogin").toString();
    }

    public void handleWebSocketDisconnect(WebSocketSession session) throws IOException {
        String userLogin = getUserLoginFromSession(session);

        if (userLogin != null) {
            log.info("Desconectando usuário: {}", userLogin);
            security.logout(userLogin);
            session.close();
        } else {
            log.warn("Falha ao tentar desconectar. Login do usuário não encontrado.");
        }
    }

   

}
