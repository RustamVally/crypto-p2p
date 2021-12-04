package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UploadSingleMediaResponseDTO;
import org.example.manager.CryptoManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {
    private final CryptoManager manager;

    @PostMapping("/bytes")
    public UploadSingleMediaResponseDTO upload(@RequestBody byte[] bytes, @RequestHeader("Content-type") String contentType) {
        return manager.save(bytes, contentType);
    }

    @PostMapping("/multipart")
    public UploadSingleMediaResponseDTO upload(@RequestPart MultipartFile file) {
        return manager.save(file);
    }
}
