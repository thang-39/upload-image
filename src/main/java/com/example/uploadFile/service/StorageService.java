package com.example.uploadFile.service;

import com.example.uploadFile.entity.ImageData;
import com.example.uploadFile.repository.StorageRepository;
import com.example.uploadFile.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storageRepository;

    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());

        if (imageData != null) {
            return "file upload successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;

    }
}
