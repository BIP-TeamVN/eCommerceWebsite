package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.CustomerDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomerDAO implements IDataGet<Long, CustomerDTO>, IDataUpdateAutoIncrement<Long, CustomerDTO> {
   private static CustomerDAO instance = null;

   private CustomerDAO() {
   }

   public static CustomerDAO getInstance() {
      if (instance == null) {
         instance = new CustomerDAO();
      }
      return instance;
   }

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
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public CustomerDTO getById(Long id) {
      String query = "SELECT * FROM CUSTOMER WHERE USER_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new CustomerDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(CustomerDTO dto) {
      Long newInsertUserId = UserDAO.getInstance().insert(dto.getUser());
      if (newInsertUserId > 0) {
         String sql = "INSERT INTO CUSTOMER(USER_ID, REGISTER_DATE) VALUES (?, ?);";
         List<Object> parameters = Arrays.asList(
                 newInsertUserId,
                 dto.getRegisterDate()
         );
         if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
            return newInsertUserId;
         }
      }
      return 0l;
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
      List<Object> parameters = Collections.singletonList(id);
      if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
         return UserDAO.getInstance().delete(id);
      }
      return 0;
   }
}
