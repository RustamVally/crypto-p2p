package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.DealRegisterRequestDTO;
import org.example.dto.DealRegisterResponseDTO;
import org.example.manager.DealManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealManager manager;

    @PostMapping("/register")
    public DealRegisterResponseDTO register(@RequestBody DealRegisterRequestDTO requestDTO) {
        return manager.register(requestDTO);
    }
}
