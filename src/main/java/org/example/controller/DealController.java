package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.DealRegisterRequestDTO;
import org.example.dto.DealRegisterResponseDTO;
import org.example.manager.DealManager;
import org.example.model.DealModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealManager manager;

    @PostMapping("/register")
    public DealRegisterResponseDTO register(@RequestBody DealRegisterRequestDTO requestDTO) {
        return manager.register(requestDTO);
    }

    // http://localhost:8080/search?text=btc
//    @RequestMapping("/search")
//    public List<DealModel> search(@RequestParam String text) {
//        return manager.search(text);
//    }
}
