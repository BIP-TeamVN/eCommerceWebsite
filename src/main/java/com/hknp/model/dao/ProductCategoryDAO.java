package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.ProductCategoryDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductCategoryDAO implements IDatabaseAccess<Long, ProductCategoryDTO> {
   @Override
   public ArrayList<ProductCategoryDTO> gets() {
      ArrayList<ProductCategoryDTO> result = new ArrayList<>();

      String query = "SELECT * FROM PRODUCT_CATEGORY;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            ProductCategoryDTO productCategoryModel = new ProductCategoryDTO(resultSet);
            result.add(productCategoryModel);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public ProductCategoryDTO getById(Long id) {
      String query = "SELECT * FROM PRODUCT_CATEGORY WHERE PRODUCT_CATEGORY_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new ProductCategoryDTO(resultSet) : null;
   }

   @Override
   public int insert(ProductCategoryDTO dto) {
      String sql = "INSERT INTO PRODUCT_CATEGORY(PRODUCT_CATEGORY_ID, PRODUCT_CATEGORY_NAME) VALUES (?, ?);";
      List<Object> parameters = Arrays.asList(dto.getProductCategoryId(), dto.getProductCategoryName());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(ProductCategoryDTO dto) {
      String sql = "UPDATE PRODUCT_CATEGORY SET PRODUCT_CATEGORY_NAME = ? WHERE PRODUCT_CATEGORY_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getProductCategoryName(), dto.getProductCategoryId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM PRODUCT_CATEGORY WHERE PRODUCT_CATEGORY_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static ProductCategoryDAO getInstance() {
      if (instance == null) {
         instance = new ProductCategoryDAO();
      }
      return instance;
   }

   private ProductCategoryDAO() {
   }

   private static ProductCategoryDAO instance = null;
}
