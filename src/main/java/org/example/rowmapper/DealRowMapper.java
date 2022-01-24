package org.example.rowmapper;


import org.example.model.DealModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DealRowMapper implements RowMapper<DealModel> {
    @Override
    public DealModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DealModel(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getDouble("balance_btc"),
                rs.getDouble("balance_rub"),
                rs.getString("dealStatus")
        );
    }
}