package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdateAutoIncrement;
import com.hknp.model.dto.BillPaymentDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BillPaymentDAO implements IDataGet<Long, BillPaymentDTO>, IDataUpdateAutoIncrement<Long, BillPaymentDTO> {
   private static BillPaymentDAO instance = null;

   private BillPaymentDAO() {
   }

   public static BillPaymentDAO getInstance() {
      if (instance == null) {
         instance = new BillPaymentDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<BillPaymentDTO> gets() {
      ArrayList<BillPaymentDTO> result = new ArrayList<>();

      String query = "SELECT * FROM BILL_PAYMENT;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            BillPaymentDTO billPaymentDTO = new BillPaymentDTO(resultSet);
            result.add(billPaymentDTO);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public BillPaymentDTO getById(Long id) {
      String query = "SELECT * FROM BILL_PAYMENT WHERE BILL_PAYMENT_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new BillPaymentDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long insert(BillPaymentDTO dto) {
      String sql = "INSERT INTO BILL_PAYMENT(BILL_ID) VALUES (?);";
      List<Object> parameters = Collections.singletonList(
              dto.getBillId()
      );
      return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
   }

   @Override
   public int update(BillPaymentDTO dto) {
      String sql = "UPDATE BILL_PAYMENT SET BILL_ID = ? WHERE BILL_PAYMENT_ID = ?";
      List<Object> parameters = Arrays.asList(
              dto.getBillPaymentId(),
              dto.getBillId()
      );
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM BILL_PAYMENT WHERE BILL_PAYMENT_ID = ?";
      List<Object> parameters = Collections.singletonList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }
}
