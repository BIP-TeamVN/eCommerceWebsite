package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.BillDetailDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BillDetailDAO implements IDataGet<Long, BillDetailDTO>, IDataUpdateAutoIncrement<Long, BillDetailDTO> {
   private static BillDetailDAO instance = null;

   private BillDetailDAO() {
   }

   public static BillDetailDAO getInstance() {
      if (instance == null) {
         instance = new BillDetailDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<BillDetailDTO> gets() {
      ArrayList<BillDetailDTO> result = new ArrayList<>();

      String query = "SELECT * FROM BILL_DETAIL;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            BillDetailDTO billDetailDTO = new BillDetailDTO(resultSet);
            result.add(billDetailDTO);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public BillDetailDTO getById(Long id) {
      String query = "SELECT * FROM BILL_DETAIL WHERE BILL_DETAIL_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new BillDetailDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(BillDetailDTO dto) {
      String sql = "INSERT INTO BILL_DETAIL(BILL_ID, PRODUCT_ID) " +
              "VALUES (?, ?, ?);";
      List<Object> parameters = Arrays.asList(
              dto.getBillId(),
              dto.getProductId()
      );
      return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
   }

   @Override
   public int update(BillDetailDTO dto) {
      String sql = "UPDATE BILL_DETAIL SET BILL_ID = ?, PRODUCT_ID = ? WHERE BILL_DETAIL_ID = ?";
      List<Object> parameters = Arrays.asList(
              dto.getBillId(),
              dto.getProductId(),
              dto.getBillDetailId()
      );
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM BILL_DETAIL WHERE BILL_DETAIL_ID = ?";
      List<Object> parameters = Collections.singletonList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }
}