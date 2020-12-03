package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.EmployeeDTO;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDAO implements IDatabaseAccess<Long, EmployeeDTO> {
   @Override
   public ArrayList<EmployeeDTO> gets() {
      ArrayList<EmployeeDTO> result = new ArrayList<>();

      String query = "SELECT * FROM EMPLOYEE;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            EmployeeDTO employeeModel = new EmployeeDTO(resultSet);
            result.add(employeeModel);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public EmployeeDTO getById(Long id) {
      String query = "SELECT * FROM EMPLOYEE WHERE USER_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new EmployeeDTO(resultSet) : null;
   }

   @Override
   public int insert(EmployeeDTO dto) {
      if (UserDAO.getInstance().insert(dto.getUser()) > 0) {
         String sql = "INSERT INTO EMPLOYEE(USER_ID, START_DATE, SALARY) VALUES (?, ?, ?);";
         List<Object> parameters = Arrays.asList(dto.getUserId(), dto.getStartDate(), dto.getSalary());
         return DatabaseUtils.executeUpdate(sql, parameters);
      }
      return 0;
   }

   @Override
   public int update(EmployeeDTO dto) {
      String sql = "UPDATE EMPLOYEE SET START_DATE = ?, SALARY = ? WHERE USER_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getStartDate(), dto.getSalary(), dto.getUserId());
      if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
         return UserDAO.getInstance().update(dto.getUser());
      }
      return 0;
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM EMPLOYEE WHERE USER_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
         return UserDAO.getInstance().delete(id);
      }
      return 0;
   }

   public static EmployeeDAO getInstance() {
      if (instance == null) {
         instance = new EmployeeDAO();
      }
      return instance;
   }

   private EmployeeDAO() {
   }

   private static EmployeeDAO instance = null;
}
