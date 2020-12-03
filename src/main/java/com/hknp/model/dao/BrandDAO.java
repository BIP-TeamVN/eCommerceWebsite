package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.BillDTO;
import com.hknp.model.dto.BrandDTO;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrandDAO implements IDatabaseAccess<Long, BrandDTO> {
   @Override
   public ArrayList<BrandDTO> gets() {
      ArrayList<BrandDTO> result = new ArrayList<>();

      String query = "SELECT * FROM BRAND;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            BrandDTO brandDTO = new BrandDTO(resultSet);
            result.add(brandDTO);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public BrandDTO getById(Long id) {
      String query = "SELECT * FROM BRAND WHERE BRAND_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new BrandDTO(resultSet) : null;
   }

   @Override
   public int insert(BrandDTO dto) {
      String sql = "INSERT INTO BRAND(BRAND_ID, BRAND_NAME, BRAND_ORIGIN) VALUES (?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getBrandId(), dto.getBrandName(), dto.getBrandOrigin());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(BrandDTO dto) {
      String sql = "UPDATE BRAND SET BRAND_NAME = ?, BRAND_ORIGIN = ? WHERE BRAND_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getBrandName(), dto.getBrandOrigin(), dto.getBrandId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM BRAND WHERE BRAND_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static BrandDAO getInstance() {
      if (instance == null) {
         instance = new BrandDAO();
      }
      return instance;
   }

   private BrandDAO() {
   }

   private static BrandDAO instance = null;
}
