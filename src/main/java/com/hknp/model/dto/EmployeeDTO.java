package com.hknp.model.dto;

import com.hknp.model.dao.UserDAO;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDTO {
   Long userId;
   UserDTO user;
   Date startDate;
   BigDecimal salary;

   public EmployeeDTO(ResultSet resultSet) {
      try {
         userId = resultSet.getLong("USER_ID");
         user = UserDAO.getInstance().getById(userId);
         startDate = resultSet.getDate("START_DATE");
         salary = resultSet.getBigDecimal("SALARY");
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
   }

   public EmployeeDTO(Long userId, UserDTO user, Date startDate, BigDecimal salary) {
      this.userId = userId;
      this.user = user;
      this.startDate = startDate;
      this.salary = salary;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public UserDTO getUser() {
      return user;
   }

   public void setUser(UserDTO user) {
      this.user = user;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public BigDecimal getSalary() {
      return salary;
   }

   public void setSalary(BigDecimal salary) {
      this.salary = salary;
   }
}
