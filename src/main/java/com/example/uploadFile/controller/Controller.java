package com.example.uploadFile.controller;

import com.example.uploadFile.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class Controller {
    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = storageService.uploadImage(file);
        return new ResponseEntity<>(uploadImage, HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @PostMapping("/fileSystem")
    public ResponseEntity<String> uploadImageToFileSystem(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = storageService.uploadImageToFileSystem(file);
        return new ResponseEntity<>(uploadImage, HttpStatus.OK);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = storageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }


}
