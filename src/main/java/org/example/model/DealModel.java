package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealModel {
    private long id;
    private String name;
    private int price;
    private double balance_btc;
    private double balance_rub;
    private String dealStatus;
}
