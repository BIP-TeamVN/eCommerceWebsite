package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.EmployeeDTO;
import com.hknp.model.dto.ProductCategoryDTO;
import com.hknp.model.dto.ProductSubCategoryDTO;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductSubCategoryDAO implements IDatabaseAccess<Long, ProductSubCategoryDTO> {
   public static ProductSubCategoryDAO getInstance() {
      if (instance == null) {
         instance = new ProductSubCategoryDAO();
      }
      return instance;
   }

   private ProductSubCategoryDAO() {
   }

   private static ProductSubCategoryDAO instance = null;

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
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public ProductSubCategoryDTO getById(Long id) {
      String query = "SELECT * FROM PRODUCT_SUB_CATEGORY WHERE PRODUCT_SUB_CATEGORY_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new ProductSubCategoryDTO(resultSet) : null;
   }

   @Override
   public int insert(ProductSubCategoryDTO dto) {
      String sql = "INSERT INTO PRODUCT_SUB_CATEGORY(PRODUCT_SUB_CATEGORY_ID, PRODUCT_CATEGORY_ID, PRODUCT_SUB_CATEGORY_NAME) VALUES (?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getProductSubCategoryId(), dto.getProductCategoryId(), dto.getProductSubCategoryName());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(ProductSubCategoryDTO dto) {
      String sql = "UPDATE PRODUCT_SUB_CATEGORY SET PRODUCT_CATEGORY_ID = ?, PRODUCT_SUB_CATEGORY_NAME = ? WHERE PRODUCT_SUB_CATEGORY_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getProductCategoryId(), dto.getProductSubCategoryName(), dto.getProductSubCategoryId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM PRODUCT_SUB_CATEGORY WHERE PRODUCT_SUB_CATEGORY_ID = " + id + ";";
      return DatabaseUtils.executeUpdate(sql, null);
   }
}
