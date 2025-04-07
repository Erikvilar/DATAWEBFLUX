package com.ltadcrm.ltadcrm.FileStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ltadcrm.ltadcrm.exceptions.FileStorageException;

@Service
@Transactional(rollbackFor = IOException.class)
public class FileStorage {

    public String saveFile(MultipartFile file, String directory,String optionalInformation) {
        Path uploadDir = Path.of("D:/data", directory);
        if (!Files.exists(uploadDir)) {
            try {
                Files.createDirectories(uploadDir);
            } catch (IOException e) {
                throw new FileStorageException("Falha ao criar diretório de upload", e);
            }
        }

        String contentType = file.getContentType();
        String extensao;
        switch (contentType) {
            case "image/jpeg":
                extensao = ".jpg";
                break;
            case "image/png":
                extensao = ".png";
                break;
            case "application/pdf":
                extensao = ".pdf";
                break;
            case "text/plain":
                extensao = ".txt";
                break;
            // Adicione outros tipos conforme necessário
            default:
                return "Tipo de arquivo não suportado: ";
        }
        // Gerar nome único para o arquivo
        String fileName = LocalDate.now() + "-" +optionalInformation+extensao.toLowerCase();
        Path filePath = uploadDir.resolve(fileName);

        // Tentar salvar o arquivo
        try {

            file.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new FileStorageException("Falha ao salvar o arquivo " + fileName, e);
        }
        if (directory.equals("patrimonios")) {
            return "http://192.168.100.5/data/patrimonios/" + fileName;
        } else if (directory.equals("pedidos")) {
            return "http://192.168.100.5/data/pedidos/" + fileName;
        } else {
            return "http://192.168.100.5/data/termos/" + fileName;
        }

    }
}
