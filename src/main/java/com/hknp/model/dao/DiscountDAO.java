package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.DiscountDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscountDAO implements IDatabaseAccess<Long, DiscountDTO> {
   @Override
   public ArrayList<DiscountDTO> gets() {
      ArrayList<DiscountDTO> result = new ArrayList<>();

      String query = "SELECT * FROM DISCOUNT;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            DiscountDTO discountDTO = new DiscountDTO(resultSet);
            result.add(discountDTO);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public DiscountDTO getById(Long id) {
      String query = "SELECT * FROM DISCOUNT WHERE DISCOUNT_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new DiscountDTO(resultSet) : null;
   }

   @Override
   public int insert(DiscountDTO dto) {
      String sql = "INSERT INTO DISCOUNT(DISCOUNT_ID, DISCOUNT_CODE, DISCOUNT_VALUE, DISCOUNT_MAX_VALUE) VALUES (?, ?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getDiscountId(), dto.getDiscountCode(), dto.getDiscountValue(), dto.getDiscountMaxValue());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(DiscountDTO dto) {
      String sql = "UPDATE DISCOUNT SET DISCOUNT_CODE = ?, DISCOUNT_VALUE = ?, DISCOUNT_MAX_VALUE = ? WHERE DISCOUNT_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getDiscountCode(), dto.getDiscountValue(), dto.getDiscountMaxValue(), dto.getDiscountId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM DISCOUNT WHERE DISCOUNT_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static DiscountDAO getInstance() {
      if (instance == null) {
         instance = new DiscountDAO();
      }
      return instance;
   }

   private DiscountDAO() {
   }

   private static DiscountDAO instance = null;
}
