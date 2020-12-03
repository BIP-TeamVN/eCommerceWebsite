package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.ProductDTO;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDAO implements IDatabaseAccess<Long, ProductDTO> {
   @Override
   public ArrayList<ProductDTO> gets() {
      ArrayList<ProductDTO> result = new ArrayList<>();

      String query = "SELECT * FROM PRODUCT;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            ProductDTO productDTO = new ProductDTO(resultSet);
            result.add(productDTO);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public ProductDTO getById(Long id) {
      String query = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new ProductDTO(resultSet) : null;
   }

   @Override
   public int insert(ProductDTO dto) {
      String sql = "INSERT INTO PRODUCT(PRODUCT_ID, BRAND_ID, SELLER_ID, PRODUCT_NAME, PRODUCT_RATE, PRODUCT_ORIGIN, PRODUCT_DESC, QUANTITY, PRICE_ORIGIN, PRICE_ORDER) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getProductId(), dto.getBrandId(), dto.getSellerId(), dto.getProductName(), dto.getProductRate(), dto.getProductOrigin(), dto.getProductDesc(), dto.getQuantity(), dto.getPriceOrigin().toEngineeringString(), dto.getPriceOrder());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(ProductDTO dto) {
      String sql = "UPDATE PRODUCT SET BRAND_ID = ?, SELLER_ID = ?, PRODUCT_NAME = ?, PRODUCT_RATE = ?, PRODUCT_ORIGIN = ?, PRODUCT_DESC = ?, QUANTITY = ?, PRICE_ORIGIN = ?, PRICE_ORDER = ? WHERE PRODUCT_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getBrandId(), dto.getSellerId(), dto.getProductName(), dto.getProductRate(), dto.getProductOrigin(), dto.getProductDesc(), dto.getQuantity(), dto.getPriceOrigin().toEngineeringString(), dto.getPriceOrder(), dto.getProductId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static ProductDAO getInstance() {
      if (instance == null) {
         instance = new ProductDAO();
      }
      return instance;
   }

   private ProductDAO() {
   }

   private static ProductDAO instance = null;
}
