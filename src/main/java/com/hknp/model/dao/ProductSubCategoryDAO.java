package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.ProductSubCategoryDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductSubCategoryDAO implements IDataGet<Long, ProductSubCategoryDTO>, IDataUpdateAutoIncrement<Long, ProductSubCategoryDTO> {

   private static ProductSubCategoryDAO instance = null;

   private ProductSubCategoryDAO() {
   }

   public static ProductSubCategoryDAO getInstance() {
      if (instance == null) {
         instance = new ProductSubCategoryDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<ProductSubCategoryDTO> gets() {
      ArrayList<ProductSubCategoryDTO> result = new ArrayList<>();

      String query = "SELECT * FROM PRODUCT_SUB_CATEGORY;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            ProductSubCategoryDTO productSubCategoryDTO = new ProductSubCategoryDTO(resultSet);
            result.add(productSubCategoryDTO);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public ProductSubCategoryDTO getById(Long id) {
      String query = "SELECT * FROM PRODUCT_SUB_CATEGORY WHERE PRODUCT_SUB_CATEGORY_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new ProductSubCategoryDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(ProductSubCategoryDTO dto) {
      String sql = "INSERT INTO PRODUCT_SUB_CATEGORY(PRODUCT_CATEGORY_ID, PRODUCT_SUB_CATEGORY_NAME) VALUES (?, ?);";
      List<Object> parameters = Arrays.asList(
              dto.getProductCategoryId(),
              dto.getProductSubCategoryName()
      );
      return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
   }

   @Override
   public int update(ProductSubCategoryDTO dto) {
      String sql = "UPDATE PRODUCT_SUB_CATEGORY SET PRODUCT_CATEGORY_ID = ?, PRODUCT_SUB_CATEGORY_NAME = ? WHERE PRODUCT_SUB_CATEGORY_ID = ?";
      List<Object> parameters = Arrays.asList(
              dto.getProductCategoryId(),
              dto.getProductSubCategoryName(),
              dto.getProductSubCategoryId()
      );
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM PRODUCT_SUB_CATEGORY WHERE PRODUCT_SUB_CATEGORY_ID = " + id + ";";
      return DatabaseUtils.executeUpdate(sql, null);
   }
}
