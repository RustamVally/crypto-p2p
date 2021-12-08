package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.OrderGetAllResponseDTO;
import org.example.dto.OrderGetByIdResponseDTO;
import org.example.dto.OrderSaveRequestDTO;
import org.example.dto.OrderSaveResponseDTO;
import org.example.manager.OrderManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderManager manager;

    @GetMapping("/getAll")
    public OrderGetAllResponseDTO getAll() {
        return manager.getAll();
    }

    @GetMapping("/getById")
    public OrderGetByIdResponseDTO getById(@RequestParam long id) {
        return manager.getById(id);
    }

    @PostMapping("/save")
    public OrderSaveResponseDTO save(@RequestBody OrderSaveRequestDTO requestDTO) {
        return manager.save(requestDTO);
    }
}

//38.38 начать