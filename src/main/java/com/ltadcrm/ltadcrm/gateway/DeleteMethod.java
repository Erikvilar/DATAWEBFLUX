package com.ltadcrm.ltadcrm.gateway;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.ltadcrm.ltadcrm.domain.Items;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteMethod {

    private final ItemsRepository itemsRepository;
    
    public ResponseEntity<String> deleteItem(List<Long> id) {

        List<Items> items = itemsRepository.findAllById(id);
        try {
            if (!items.isEmpty()) {
                itemsRepository.deleteAll(items);
                return ResponseEntity.ok("Linha  foi deletada");
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao deletar " + e);
        }

    }
}
