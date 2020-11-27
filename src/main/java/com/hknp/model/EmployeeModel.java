package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    Long userId;

    public EmployeeModel (ResultSet resultSet) throws SQLException {
        userId = resultSet.getLong("");
    }
}
