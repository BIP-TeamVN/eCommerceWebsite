package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.SellerDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SellerDAO implements IDataGet<Long, SellerDTO>, IDataUpdateAutoIncrement<Long, SellerDTO> {
   private static SellerDAO instance = null;

   private SellerDAO() {
   }

   public static SellerDAO getInstance() {
      if (instance == null) {
         instance = new SellerDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<SellerDTO> gets() {
      ArrayList<SellerDTO> result = new ArrayList<>();

      String query = "SELECT * FROM SELLER;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            SellerDTO sellerModel = new SellerDTO(resultSet);
            result.add(sellerModel);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public SellerDTO getById(Long id) {
      String query = "SELECT * FROM SELLER WHERE USER_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new SellerDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(SellerDTO dto) {
      Long newInsertUserId = UserDAO.getInstance().insert(dto.getUser());
      if (newInsertUserId > 0) {
         String sql = "INSERT INTO SELLER(USER_ID, STORE_NAME, STORE_LINK, BUSINESS_LICENSE_ID, SELLER_CATEGORY_ID, BANK_ACCOUNT_ID) " +
                 "VALUES (?, ?, ?, ?, ?, ?);";
         List<Object> parameters = Arrays.asList(
                 newInsertUserId,
                 dto.getStoreName(),
                 dto.getStoreLink(),
                 dto.getBusinessLicenseId(),
                 dto.getSellerCategoryId(),
                 dto.getBankAccountId()
         );
         if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
            return newInsertUserId;
         }
      }
      return 0l;
   }

   @Override
   public int update(SellerDTO dto) {
      String sql = "UPDATE SELLER SET STORE_NAME = ?, STORE_LINK = ?, BUSINESS_LICENSE_ID = ?, SELLER_CATEGORY_ID = ?, BANK_ACCOUNT_ID = ? WHERE USER_ID = ?";
      List<Object> parameters = Arrays.asList(
              dto.getStoreName(),
              dto.getStoreLink(),
              dto.getBusinessLicenseId(),
              dto.getSellerCategoryId(),
              dto.getBankAccountId(),
              dto.getUserId()
      );
      if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
         return UserDAO.getInstance().update(dto.getUser());
      }
      return 0;
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM SELLER WHERE USER_ID = ?";
      List<Object> parameters = Collections.singletonList(id);
      if (DatabaseUtils.executeUpdate(sql, parameters) > 0) {
         return UserDAO.getInstance().delete(id);
      }
      return 0;
   }
}
