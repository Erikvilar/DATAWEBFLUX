package com.ltadcrm.ltadcrm.events;

import java.time.LocalDateTime;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import com.ltadcrm.ltadcrm.domain.logs.Audit;
import com.ltadcrm.ltadcrm.events.Items.ItemUpdatedEvent;
import com.ltadcrm.ltadcrm.repositories.AuditRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemsEventListener {

    private final AuditRepository auditRepository;



    @EventListener
    public void itemUpdated(ItemUpdatedEvent event) {
        

        Audit logs = new Audit();

        logs.setId_item(event.getItemId());
        logs.setEmail_user(event.getUser());
        logs.setType_action("update");
        logs.setName_item_new(event.getDescription());
        logs.setName_item_old(event.getOldDescription());
        logs.setUpdate_in(LocalDateTime.now());

        auditRepository.save(logs);
        log.info("Items salvo no log de eventos: , {}, {}, {}", event.getItemId(), event.getDescription(), event.getUser());

    }
    
    

   
}