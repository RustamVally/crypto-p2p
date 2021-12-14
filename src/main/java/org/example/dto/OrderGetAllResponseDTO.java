package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderGetAllResponseDTO {
    private List<Order> orders;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Order {
        private long id;
        private String salesman;
        private int price;
        private double bitcoin;
        private int review;
        private int min_amount;
        private int max_amount;
        private int successful_deals;
        private String status;
        private String proStatus;
    }
}
