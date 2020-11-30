package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.UserDTO;
import com.hknp.utils.DatabaseUtils;
import com.hknp.utils.FormatUtils;

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
            UserDTO userDTO = new UserDTO(resultSet);
            result.add(userDTO);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public UserDTO getById(Long id) {
      return null;
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
      return 0;
   }

   @Override
   public int delete(Long id) {
      return 0;
   }

   public static UserDAO getInstance() {
      if (instance == null) {
         instance = new UserDAO();
      }
      return instance;
   }

   private UserDAO() {
   }

   private static UserDAO instance = null;
}
