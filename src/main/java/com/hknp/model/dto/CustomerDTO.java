package com.hknp.model.dto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDTO {
    Long userId;
    Date registerDate;

    public CustomerDTO (ResultSet resultSet) throws SQLException {
        userId = resultSet.getLong("USER_ID");
        registerDate = resultSet.getDate("REGISTER_DATE");
    }
}
