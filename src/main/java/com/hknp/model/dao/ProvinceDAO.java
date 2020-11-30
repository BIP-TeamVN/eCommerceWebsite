package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProvinceDAO implements IDatabaseAccess<String, ProvinceDTO> {

   @Override
   public ArrayList<ProvinceDTO> gets() {
      ArrayList<ProvinceDTO> result = new ArrayList<>();

      String query = "SELECT * FROM PROVINCE;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            ProvinceDTO provinceModel = new ProvinceDTO(resultSet);
            result.add(provinceModel);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public ProvinceDTO getById(String id) {
      String query = "SELECT * FROM PROVINCE WHERE PROVINCE_ID = '" + id + "';";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new ProvinceDTO(resultSet) : null;
   }

   @Override
   public Object insert(ProvinceDTO dto) {
      String sql = "INSERT INTO PROVINCE(PROVINCE_ID, PROVINCE_NAME, PROVINCE_TYPE) VALUES (?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getProvinceId(), dto.getProvinceName(), dto.getProvinceType());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(ProvinceDTO dto) {
      String sql = "UPDATE PROVINCE SET PROVINCE_NAME = ?, PROVINCE_TYPE = ? WHERE PROVINCE_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getProvinceName(), dto.getProvinceType(), dto.getProvinceId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(String id) {
      String sql = "DELETE FROM PROVINCE WHERE PROVINCE_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static ProvinceDAO getInstance() {
      if (instance == null) {
         instance = new ProvinceDAO();
      }
      return instance;
   }

   private ProvinceDAO() {
   }

   private static ProvinceDAO instance = null;
}
