package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealRegisterRequestDTO {
    private long id;
    private String name;
    private int price;
    private double balance_btc;
    private int balance_rub;
    private String dealStatus;
}
