package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.BillDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BillDAO implements IDataGet<Long, BillDTO>, IDataUpdateAutoIncrement<Long, BillDTO> {
   private static BillDAO instance = null;

   private BillDAO() {
   }

   public static BillDAO getInstance() {
      if (instance == null) {
         instance = new BillDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<BillDTO> gets() {
      ArrayList<BillDTO> result = new ArrayList<>();

      String query = "SELECT * FROM BILL;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            BillDTO billDTO = new BillDTO(resultSet);
            result.add(billDTO);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public BillDTO getById(Long id) {
      String query = "SELECT * FROM BILL WHERE BILL_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new BillDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(BillDTO dto) {
      String sql = "INSERT INTO BILL(CUSTOMER_ID, ADDRESS_ID, DISCOUNT_ID) " +
              "VALUES (?, ?, ?);";
      List<Object> parameters = Arrays.asList(
              dto.getCustomerId(),
              dto.getAddressId(),
              dto.getDiscountId()
      );
      return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
   }

   @Override
   public int update(BillDTO dto) {
      String sql = "UPDATE BILL SET ADDRESS_ID = ?, DISCOUNT_ID = ? WHERE BILL_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getAddressId(), dto.getDiscountId(), dto.getBillId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM BILL WHERE BILL_ID = ?";
      List<Object> parameters = Collections.singletonList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }
}
