package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminModel {
    Long userId;

    public AdminModel(ResultSet resultSet) throws SQLException {
        userId = resultSet.getLong("USER_ID");
    }
}
