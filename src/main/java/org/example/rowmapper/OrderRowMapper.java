package org.example.rowmapper;

import org.example.model.OrderModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderRowMapper implements RowMapper<OrderModel> {
    @Override
    public OrderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrderModel(
                rs.getLong("id"),
                rs.getString("salesman"),
                rs.getInt("price"),
                rs.getInt("qty"),
                rs.getInt("review"),
                rs.getInt("min_amount"),
                rs.getInt("max_amount"),
                rs.getInt("successful_deals"),
                rs.getString("status"),
                rs.getString("proStatus")
        );
    }
}
