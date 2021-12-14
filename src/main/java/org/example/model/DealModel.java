package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealModel {
    private long id;
    private String salesman;
    private int price;
    private double balance_btc;
    private int balance_rub;
    private String dealStatus;
}
