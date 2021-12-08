package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderModel {
    private long id;
    private String salesman;
    private int price;
    private int qty;
    private int review;
    private int min_amount;
    private int max_amount;
    private int successful_deals;
    private String status;
    private String proStatus;

}
