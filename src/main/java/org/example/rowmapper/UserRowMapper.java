package org.example.rowmapper;

import org.example.model.UserModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserModel(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("balance_btc"),
                rs.getInt("balance_rub"),
                rs.getDouble("rating")
        );
    }
}
