package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.model.dto.SellerCategoryDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SellerCategoryDAO implements IDatabaseAccess<Long, SellerCategoryDTO> {
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
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public SellerCategoryDTO getById(Long id) {
      String query = "SELECT * FROM SELLER_CATEGORY WHERE SELLER_CATEGORY_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new SellerCategoryDTO(resultSet) : null;
   }

   @Override
   public int insert(SellerCategoryDTO dto) {
      String sql = "INSERT INTO SELLER_CATEGORY(SELLER_CATEGORY_ID, CATEGORY_NAME) VALUES (?, ?);";
      List<Object> parameters = Arrays.asList(dto.getSellerCategoryId(), dto.getCategoryName());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(SellerCategoryDTO dto) {
      String sql = "UPDATE SELLER_CATEGORY SET CATEGORY_NAME = ? WHERE SELLER_CATEGORY_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getCategoryName(), dto.getSellerCategoryId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM SELLER_CATEGORY WHERE SELLER_CATEGORY_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static SellerCategoryDAO getInstance() {
      if (instance == null) {
         instance = new SellerCategoryDAO();
      }
      return instance;
   }

   private SellerCategoryDAO() {
   }

   private static SellerCategoryDAO instance = null;
}
