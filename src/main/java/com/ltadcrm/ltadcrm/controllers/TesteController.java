package com.ltadcrm.ltadcrm.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ltadcrm.ltadcrm.domain.Teste;
import com.ltadcrm.ltadcrm.repositories.TesteRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("teste")
@RequiredArgsConstructor
public class TesteController {
    @Value("${upload.dir:C:/nginx/images}")
    private String uploadDir;
    private final TesteRepository repository;

    @PostMapping("/image")
    public String uploadImage(
        @RequestParam("nome") String nome,
        @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "Arquivo não selecionado!";
        }

        // Cria o diretório de upload, se necessário
        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
        // Define o caminho completo do arquivo a ser salvo
        String filename = file.getOriginalFilename();
        Path filePath = Path.of(uploadDir, filename);

        // Copia o arquivo para o diretório de destino
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Aqui você pode salvar o caminho da imagem no banco de dados
        String fileUrl = "/images/" + filename; // Caminho relativo, para ser usado pelo Nginx
        Teste teste = new Teste();
        teste.setNome(nome);
        teste.setImagem("http://10.2.128.20"+fileUrl);
        repository.save(teste);


        return "Arquivo enviado com sucesso: " + fileUrl;
    }
}
