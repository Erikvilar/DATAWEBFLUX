package com.ltadcrm.ltadcrm.usecases;

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
    
    public String deleteItem(List<Long> id) {

        List<Items> items = itemsRepository.findAllById(id);
        try {
            if (!items.isEmpty()) {
                itemsRepository.deleteAll(items);
                return "Linha  foi deletada";
            }
            return "NO CONTENT";
        } catch (Exception e) {
            return "Ocorreu um erro ao deletar ";
        }

    }
}
