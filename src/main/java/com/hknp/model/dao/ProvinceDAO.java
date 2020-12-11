package com.hknp.model.dao;

import com.hknp.interfaces.IDataGet;
import com.hknp.interfaces.IDataUpdate;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProvinceDAO implements IDataGet<String, ProvinceDTO>, IDataUpdate<String, ProvinceDTO> {
   private static ProvinceDAO instance = null;

   private ProvinceDAO() {
   }

   public static ProvinceDAO getInstance() {
      if (instance == null) {
         instance = new ProvinceDAO();
      }
      return instance;
   }

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
      } catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public ProvinceDTO getById(String id) {
      String query = "SELECT * FROM PROVINCE WHERE PROVINCE_ID = '" + id + "';";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      try {
         if (resultSet != null && resultSet.next()) {
            return new ProvinceDTO(resultSet);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public int insert(ProvinceDTO dto) {
      String sql = "INSERT INTO `PROVINCE` (`PROVINCE_ID`, `PROVINCE_NAME`, `PROVINCE_TYPE`) " +
              "SELECT * FROM (SELECT ?, ?, ?) AS `TEMP` " +
              "WHERE NOT EXISTS (SELECT `PROVINCE_ID` FROM `PROVINCE` WHERE `PROVINCE_ID` = ?) LIMIT 1;";
      List<Object> parameters = Arrays.asList(
              dto.getProvinceId(),
              dto.getProvinceName(),
              dto.getProvinceType(),
              dto.getProvinceId()
      );
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(ProvinceDTO dto) {
      String sql = "UPDATE PROVINCE SET PROVINCE_NAME = ?, PROVINCE_TYPE = ? WHERE PROVINCE_ID = ?";
      List<Object> parameters = Arrays.asList(
              dto.getProvinceName(),
              dto.getProvinceType(),
              dto.getProvinceId()
      );
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(String id) {
      String sql = "DELETE FROM PROVINCE WHERE PROVINCE_ID = ?";
      List<Object> parameters = Collections.singletonList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }
}
