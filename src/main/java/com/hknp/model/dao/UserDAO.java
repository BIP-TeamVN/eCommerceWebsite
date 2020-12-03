package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.UserDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO implements IDatabaseAccess<Long, UserDTO> {
   @Override
   public ArrayList<UserDTO> gets() {
      ArrayList<UserDTO> result = new ArrayList<>();

      String query = "SELECT * FROM USER;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            UserDTO userModel = new UserDTO(resultSet);
            result.add(userModel);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public UserDTO getById(Long id) {
      String query = "SELECT * FROM USER WHERE USER_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new UserDTO(resultSet) : null;
   }

   @Override
   public Object insert(UserDTO dto) {
      String sql = "INSERT INTO USER(LAST_NAME, FIRST_NAME, GENDER, DATE_OF_BIRTH, SSN, IMAGE_PATH, PHONE_NUMBER, EMAIL, USER_NAME, PASSWORD, USER_TYPE)" +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

      List<Object> parameters = Arrays.asList(
              dto.getLastName(),
              dto.getFirstName(),
              dto.getGender(),
              FormatUtils.dateToString(dto.getDateOfBirth(), "yyyyMMdd"),
              dto.getSsn(),
              dto.getImagePath(),
              dto.getPhoneNumber(),
              dto.getEmail(),
              dto.getUserName(),
              dto.getPassword(),
              dto.getUserType()
              );
      return DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
   }

   @Override
   public int update(UserDTO dto) {
      String sql = "UPDATE USER SET LAST_NAME = ?, FIRST_NAME = ?, GENDER = ?, DATE_OF_BIRTH = ?, SSN = ?, IMAGE_PATH = ?, PHONE_NUMBER = ?, EMAIL = ?, USER_NAME = ?, PASSWORD = ?, USER_TYPE = ?, STATUS = ? WHERE USER_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getLastName(), dto.getFirstName(), dto.getGender(), dto.getDateOfBirth(), dto.getSsn(), dto.getImagePath(), dto.getPhoneNumber(), dto.getEmail(), dto.getUserName(), dto.getPassword(), dto.getUserType(), dto.getStatus(), dto.getUserId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM USER WHERE USER_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static UserDAO getInstance() {
      if (instance == null) {
         instance = new UserDAO();
      }
      return instance;
   }

   private UserDAO () {
   }

   private static UserDAO instance = null;
}
