package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UploadMultipleMediaResponseDTO;
import org.example.dto.UploadSingleMediaResponseDTO;
import org.example.manager.MediaManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaManager manager;

    @PostMapping("/bytes")
    public UploadSingleMediaResponseDTO upload(@RequestBody byte[] bytes, @RequestHeader("Content-type") String contentType) {
        return manager.save(bytes, contentType);
    }

    @PostMapping("/multipart")
    public UploadSingleMediaResponseDTO upload(@RequestPart MultipartFile file) {
        return manager.save(file);
    }

    @PostMapping("/verification")
    public UploadMultipleMediaResponseDTO upload(@RequestPart List<MultipartFile> files) {
        return manager.save(files);
    }
}
