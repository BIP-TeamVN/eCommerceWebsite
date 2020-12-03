package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.EmployeeDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeDAO implements IDataGet<Long, EmployeeDTO>, IDataUpdateAutoIncrement<Long, EmployeeDTO> {
   private static EmployeeDAO instance = null;

   private EmployeeDAO() {
   }

   public static EmployeeDAO getInstance() {
      if (instance == null) {
         instance = new EmployeeDAO();
      }
      return instance;
   }

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
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public EmployeeDTO getById(Long id) {
      String query = "SELECT * FROM EMPLOYEE WHERE USER_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new EmployeeDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(EmployeeDTO dto) {
      Long newInsertUserId = UserDAO.getInstance().insert(dto.getUser());
      if (newInsertUserId > 0) {
         String sql = "INSERT INTO EMPLOYEE(USER_ID, START_DATE, SALARY) VALUES (?, ?, ?);";
         List<Object> parameters = Arrays.asList(
                 newInsertUserId,
                 dto.getStartDate(),
                 dto.getSalary()
         );
         if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
            return newInsertUserId;
         }
      }
      return 0l;
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
      List<Object> parameters = Collections.singletonList(id);
      if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
         return UserDAO.getInstance().delete(id);
      }
      return 0;
   }
}
