package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealRegisterResponseDTO {
    private Deal deal;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Deal {
        private long id;
        private String name;
        private int price;
        private double balance_btc;
        private double balance_rub;
        private String dealStatus;
    }
}
