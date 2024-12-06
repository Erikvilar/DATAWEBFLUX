package com.ltadcrm.ltadcrm.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.ltadcrm.ltadcrm.events.Items.ItemUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemsEventListener {

    @EventListener
    public void itemUpdated(ItemUpdatedEvent event) {
        log.info("Dados do item, {}", event);

    }

}