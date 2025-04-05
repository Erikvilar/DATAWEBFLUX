package com.ltadcrm.ltadcrm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ltadcrm.ltadcrm.FileStorage.FileStorage;
import com.ltadcrm.ltadcrm.domain.Receiving;
import com.ltadcrm.ltadcrm.repositories.ItemsRepository;
import com.ltadcrm.ltadcrm.repositories.ReceivingRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileStorage fileStorage;
    private final ItemsRepository itemsRepository;
    private final ReceivingRepository receivingRepository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFiles(
            @RequestPart("pedidoPDF") MultipartFile pedido,
            @RequestPart("termoPDF") MultipartFile termo,
            @RequestPart("images") List<MultipartFile> files,
            @RequestParam("codigo_item") List<String> codigo) {

        System.out.println(files.size());

     
        var receiving = itemsRepository.findBynumber(codigo.get(0)).get();
        Receiving receivingSaved = receivingRepository.findById(receiving.getReceiving().getReceivingID()).get();
        receivingSaved.setPdfOrder(fileStorage.saveFile(pedido, "pedidos",receiving.getReceiving().getReceivingCode().toString()));
        receivingSaved.setPdfTerm(fileStorage.saveFile(termo, "termos",receiving.getReceiving().getReceivingCode().toString()));
        receivingRepository.save(receivingSaved);

        int totalPares = Math.min(codigo.size(), files.size() / 2);
        for (int i = 0; i < totalPares; i++) {
            int index1 = i * 2;
            int index2 = i * 2 + 1;
            var items = itemsRepository.findBynumber(codigo.get(i)).get();
            List<String> imagePair = new ArrayList<>();
            imagePair.clear();
            System.out.println("teste");
            if (index1 < files.size()) {
                imagePair.add(fileStorage.saveFile(files.get(index1), "patrimonios",items.getNumber()));
            }
            if (index2 < files.size()) {
                imagePair.add(fileStorage.saveFile(files.get(index2), "patrimonios", items.getNumber()));
            }
           
            items.setPathImage(imagePair);
            itemsRepository.save(items);

        }

        return ResponseEntity.ok("Arquivos enviados com sucesso!");
    }

}
