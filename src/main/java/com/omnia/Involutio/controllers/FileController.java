package com.omnia.Involutio.controllers;

import com.omnia.Involutio.service.file.FileMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@Slf4j
@RequestMapping("/files")
public class FileController {
    final private FileMaster fileMaster;

    public FileController(FileMaster fileMaster) {
        this.fileMaster = fileMaster;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(fileMaster.getAll());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        // Добавьте вашу логику обработки файла здесь
        var fileEntity = fileMaster.create(file);
        // Возвращаем ответ с кодом 200 OK в случае успешной обработки файла
        return ResponseEntity.ok(fileEntity.toString());
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam ("fileId") Long fileId) throws IOException {
        // Путь к файлу, который вы хотите скачать
        var file = fileMaster.download(fileId);

        var resource = new InputStreamResource(Files.newInputStream(file));

        // Определите MIME-тип файла
        String mimeType = Files.probeContentType(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName().toString())
                .header(HttpHeaders.CONTENT_TYPE, mimeType)
                .body(resource);
    }
}
