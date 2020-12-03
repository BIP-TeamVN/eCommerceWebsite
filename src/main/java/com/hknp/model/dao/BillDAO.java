package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.BillDTO;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillDAO implements IDatabaseAccess<Long, BillDTO> {
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
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public BillDTO getById(Long id) {
      String query = "SELECT * FROM BILL WHERE BILL_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new BillDTO(resultSet) : null;
   }

   @Override
   public int insert(BillDTO dto) {
      String sql = "INSERT INTO BILL(BILL_ID, ADDRESS_ID, DISCOUNT_ID) VALUES (?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getBillId(), dto.getAddressId(), dto.getDiscountId());
      return DatabaseUtils.executeUpdate(sql, parameters);
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
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static BillDAO getInstance() {
      if (instance == null) {
         instance = new BillDAO();
      }
      return instance;
   }

   private BillDAO() {
   }

   private static BillDAO instance = null;
}
