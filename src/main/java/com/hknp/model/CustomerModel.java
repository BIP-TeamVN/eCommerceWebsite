package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    Long userId;

    public CustomerModel (ResultSet resultSet) throws SQLException {
        userId = resultSet.getLong("USER_ID");
    }
}
