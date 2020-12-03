package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.DiscountDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiscountDAO implements IDataGet<Long, DiscountDTO>, IDataUpdateAutoIncrement<Long, DiscountDTO> {
   private static DiscountDAO instance = null;

   private DiscountDAO() {
   }

   public static DiscountDAO getInstance() {
      if (instance == null) {
         instance = new DiscountDAO();
      }
      return instance;
   }

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
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public DiscountDTO getById(Long id) {
      String query = "SELECT * FROM DISCOUNT WHERE DISCOUNT_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new DiscountDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(DiscountDTO dto) {
      String sql = "INSERT INTO DISCOUNT(DISCOUNT_CODE, DISCOUNT_VALUE, DISCOUNT_MAX_VALUE) " +
              "VALUES (?, ?, ?);";
      List<Object> parameters = Arrays.asList(
              dto.getDiscountCode(),
              dto.getDiscountValue(),
              dto.getDiscountMaxValue()
      );
      return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
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
      List<Object> parameters = Collections.singletonList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }
}
