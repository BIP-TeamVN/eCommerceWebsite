package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.ProductCategoryDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductCategoryDAO implements IDataGet<Long, ProductCategoryDTO>, IDataUpdateAutoIncrement<Long, ProductCategoryDTO> {
   private static ProductCategoryDAO instance = null;

   private ProductCategoryDAO() {
   }

   public static ProductCategoryDAO getInstance() {
      if (instance == null) {
         instance = new ProductCategoryDAO();
      }
      return instance;
   }

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
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public ProductCategoryDTO getById(Long id) {
      String query = "SELECT * FROM PRODUCT_CATEGORY WHERE PRODUCT_CATEGORY_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new ProductCategoryDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(ProductCategoryDTO dto) {
      String sql = "INSERT INTO PRODUCT_CATEGORY(PRODUCT_CATEGORY_NAME) VALUES (?);";
      List<Object> parameters = Collections.singletonList(dto.getProductCategoryName());
      return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
   }

   @Override
   public int update(ProductCategoryDTO dto) {
      String sql = "UPDATE PRODUCT_CATEGORY SET PRODUCT_CATEGORY_NAME = ? WHERE PRODUCT_CATEGORY_ID = ?";
      List<Object> parameters = Arrays.asList(
              dto.getProductCategoryName(),
              dto.getProductCategoryId()
      );
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM PRODUCT_CATEGORY WHERE PRODUCT_CATEGORY_ID = ?";
      List<Object> parameters = Collections.singletonList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }
}
