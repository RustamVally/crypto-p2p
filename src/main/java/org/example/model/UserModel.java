package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserModel {
    private long id;
    private String name;
    private double balance_btc;
    private int balance_rub;
    private double rating;
}
