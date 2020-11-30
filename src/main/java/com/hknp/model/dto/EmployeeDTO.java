package com.hknp.model.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDTO {
   Long userId;
   Date startDate;
   BigDecimal salary;

   public EmployeeDTO (ResultSet resultSet) throws SQLException {
      userId = resultSet.getLong("USER_ID");
      startDate = resultSet.getDate("START_DATE");
      salary = resultSet.getBigDecimal("SALARY");
   }
}
