package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.BillPaymentDTO;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillPaymentDAO implements IDatabaseAccess<Long, BillPaymentDTO> {
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
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public BillPaymentDTO getById(Long id) {
      String query = "SELECT * FROM BILL_PAYMENT WHERE BILL_PAYMENT_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new BillPaymentDTO(resultSet) : null;
   }

   @Override
   public int insert(BillPaymentDTO dto) {
      String sql = "INSERT INTO BILL_PAYMENT(BILL_PAYMENT_ID, BILL_ID) VALUES (?, ?);";
      List<Object> parameters = Arrays.asList(dto.getBillPaymentId(), dto.getBillId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(BillPaymentDTO dto) {
      String sql = "UPDATE BILL_PAYMENT SET BILL_ID = ? WHERE BILL_PAYMENT_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getBillPaymentId(), dto.getBillId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM BILL_PAYMENT WHERE BILL_PAYMENT_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static BillPaymentDAO getInstance() {
      if (instance == null) {
         instance = new BillPaymentDAO();
      }
      return instance;
   }

   private BillPaymentDAO() {
   }

   private static BillPaymentDAO instance = null;
}
