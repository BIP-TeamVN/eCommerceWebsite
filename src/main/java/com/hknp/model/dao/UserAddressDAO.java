package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.model.dto.UserAddressDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAddressDAO implements IDatabaseAccess<Long, UserAddressDTO> {
   @Override
   public ArrayList<UserAddressDTO> gets() {
      ArrayList<UserAddressDTO> result = new ArrayList<>();

      String query = "SELECT * FROM USER_ADDRESS;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            UserAddressDTO userAddressModel = new UserAddressDTO(resultSet);
            result.add(userAddressModel);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public UserAddressDTO getById(Long id) {
      String query = "SELECT * FROM USER_ADDRESS WHERE ADDRESS_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new UserAddressDTO(resultSet) : null;
   }

   @Override
   public int insert(UserAddressDTO dto) {
      String sql = "INSERT INTO USER_ADDRESS(USER_ID, ADDRESS_ID, FULL_NAME, PHONE_NUMBER, ADDRESS_NAME) VALUES (?, ?, ?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getUserId(), dto.getAddressId(), dto.getFullName(), dto.getPhoneNumber(), dto.getAddressName());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(UserAddressDTO dto) {
      String sql = "UPDATE USER_ADDRESS SET USER_ID = ?, FULL_NAME = ?, PHONE_NUMBER = ?, ADDRESS_NAME = ? WHERE ADDRESS_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getUserId(), dto.getFullName(), dto.getPhoneNumber(), dto.getAddressName(), dto.getAddressId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM USER_ADDRESS WHERE ADDRESS_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static UserAddressDAO getInstance() {
      if (instance == null) {
         instance = new UserAddressDAO();
      }
      return instance;
   }

   private UserAddressDAO() {
   }

   private static UserAddressDAO instance = null;
}
