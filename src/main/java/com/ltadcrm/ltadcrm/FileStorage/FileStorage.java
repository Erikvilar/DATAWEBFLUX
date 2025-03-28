package com.ltadcrm.ltadcrm.FileStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ltadcrm.ltadcrm.exceptions.FileStorageException;
@Service
 @Transactional(rollbackFor = IOException.class)
public class FileStorage {

    public String saveFile(MultipartFile file, String directory) {
        Path uploadDir = Path.of("C:/nginx/data", directory);
        if (!Files.exists(uploadDir)) {
            try {
                Files.createDirectories(uploadDir);
            } catch (IOException e) {
                throw new FileStorageException("Falha ao criar diretório de upload", e);
            }
        }
    
        // Gerar nome único para o arquivo
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(fileName);
    
        // Tentar salvar o arquivo
        try {
            file.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new FileStorageException("Falha ao salvar o arquivo " + fileName, e);
        }
    
        return filePath.toString();
    }
}
