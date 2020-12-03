package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.CustomerDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerDAO implements IDatabaseAccess<Long, CustomerDTO> {
   @Override
   public ArrayList<CustomerDTO> gets() {
      ArrayList<CustomerDTO> result = new ArrayList<>();

      String query = "SELECT * FROM CUSTOMER;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            CustomerDTO customerModel = new CustomerDTO(resultSet);
            result.add(customerModel);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public CustomerDTO getById(Long id) {
      String query = "SELECT * FROM CUSTOMER WHERE USER_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new CustomerDTO(resultSet) : null;
   }

   @Override
   public int insert(CustomerDTO dto) {
      if (UserDAO.getInstance().insert(dto.getUser()) > 0) {
         String sql = "INSERT INTO CUSTOMER(USER_ID, REGISTER_DATE) VALUES (?, ?);";
         List<Object> parameters = Arrays.asList(dto.getUserId(), dto.getRegisterDate());
         return DatabaseUtils.executeUpdate(sql, parameters);
      }
      return 0;
   }

   @Override
   public int update(CustomerDTO dto) {
      String sql = "UPDATE CUSTOMER SET REGISTER_DATE = ? WHERE USER_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getRegisterDate(), dto.getUserId());
      if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
         return UserDAO.getInstance().update(dto.getUser());
      }
      return 0;
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM CUSTOMER WHERE USER_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
         return UserDAO.getInstance().delete(id);
      }
      return 0;
   }

   public static CustomerDAO getInstance() {
      if (instance == null) {
         instance = new CustomerDAO();
      }
      return instance;
   }

   private CustomerDAO() {
   }

   private static CustomerDAO instance = null;
}
