package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.BillDetailDTO;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillDetailDAO implements IDatabaseAccess<Long, BillDetailDTO> {
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
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public BillDetailDTO getById(Long id) {
      String query = "SELECT * FROM BILL_DETAIL WHERE BILL_DETAIL_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new BillDetailDTO(resultSet) : null;
   }

   @Override
   public int insert(BillDetailDTO dto) {
      String sql = "INSERT INTO BILL_DETAIL(BILL_DETAIL_ID, BILL_ID, PRODUCT_ID) VALUES (?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getBillDetailId(), dto.getBillId(), dto.getProductId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(BillDetailDTO dto) {
      String sql = "UPDATE BILL_DETAIL SET BILL_ID = ?, PRODUCT_ID = ? WHERE BILL_DETAIL_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getBillId(), dto.getProductId(), dto.getBillDetailId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM BILL_DETAIL WHERE BILL_DETAIL_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static BillDetailDAO getInstance() {
      if (instance == null) {
         instance = new BillDetailDAO();
      }
      return instance;
   }

   private BillDetailDAO() {
   }

   private static BillDetailDAO instance = null;
}
