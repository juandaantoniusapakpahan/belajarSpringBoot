package com.spring.fileupload.controller;

import com.spring.fileupload.model.response.UploadResponse;
import com.spring.fileupload.service.FileStorageService;
import com.spring.fileupload.service.impl.FileStorageServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {


    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageServiceImpl fileStorageService){
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestParam(name ="file", required = false)MultipartFile file,
            @RequestParam(name = "fullName", required = true) String fullName,
            @RequestParam(name = "dateOfBirth", required = true) String dateOfBirth
            ){
        String fileName =fileStorageService.storeFile(file);
        UploadResponse uploadResponse = new UploadResponse(fileName, fullName, dateOfBirth);
        return ResponseEntity.ok().body(uploadResponse);
    }
}
