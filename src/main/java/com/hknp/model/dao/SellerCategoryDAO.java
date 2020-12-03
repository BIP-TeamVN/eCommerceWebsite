package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.SellerCategoryDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SellerCategoryDAO implements IDataGet<Long, SellerCategoryDTO>, IDataUpdateAutoIncrement<Long, SellerCategoryDTO> {
   private static SellerCategoryDAO instance = null;

   private SellerCategoryDAO() {
   }

   public static SellerCategoryDAO getInstance() {
      if (instance == null) {
         instance = new SellerCategoryDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<SellerCategoryDTO> gets() {
      ArrayList<SellerCategoryDTO> result = new ArrayList<>();

      String query = "SELECT * FROM SELLER_CATEGORY;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            SellerCategoryDTO sellerCategoryModel = new SellerCategoryDTO(resultSet);
            result.add(sellerCategoryModel);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public SellerCategoryDTO getById(Long id) {
      String query = "SELECT * FROM SELLER_CATEGORY WHERE SELLER_CATEGORY_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new SellerCategoryDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(SellerCategoryDTO dto) {
      String sql = "INSERT INTO SELLER_CATEGORY(CATEGORY_NAME) VALUES (?);";
      List<Object> parameters = Arrays.asList(
              dto.getCategoryName()
      );
      return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
   }

   @Override
   public int update(SellerCategoryDTO dto) {
      String sql = "UPDATE SELLER_CATEGORY SET CATEGORY_NAME = ? WHERE SELLER_CATEGORY_ID = ?";
      List<Object> parameters = Arrays.asList(
              dto.getCategoryName(),
              dto.getSellerCategoryId()
      );
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM SELLER_CATEGORY WHERE SELLER_CATEGORY_ID = ?";
      List<Object> parameters = Collections.singletonList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }
}
