package com.hknp.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    Long userId;
    Date registerDate;

    public CustomerModel (ResultSet resultSet) throws SQLException {
        userId = resultSet.getLong("USER_ID");
        registerDate = resultSet.getDate("REGISTER_DATE");
    }
}
