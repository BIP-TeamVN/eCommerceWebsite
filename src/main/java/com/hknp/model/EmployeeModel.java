package com.hknp.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    Long userId;
    Date startDate;
    BigDecimal salary;

    public EmployeeModel (ResultSet resultSet) throws SQLException {
        userId = resultSet.getLong("USER_ID");
        startDate = resultSet.getDate("START_DATE");
        salary = resultSet.getBigDecimal("SALARY");
    }
}
