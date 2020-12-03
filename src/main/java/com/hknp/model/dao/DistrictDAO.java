package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.DistrictDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistrictDAO implements IDatabaseAccess<String, DistrictDTO> {

   @Override
   public ArrayList<DistrictDTO> gets() {
      ArrayList<DistrictDTO> result = new ArrayList<>();

      String query = "SELECT * FROM DISTRICT;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            DistrictDTO districtModel = new DistrictDTO(resultSet);
            result.add(districtModel);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }
      return result;
   }

   @Override
   public DistrictDTO getById(String id) {
      String query = "SELECT * FROM DISTRICT WHERE DISTRICT_ID = '" + id + "';";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new DistrictDTO(resultSet) : null;
   }

   @Override
   public int insert(DistrictDTO dto) {
      String sql = "INSERT INTO DISTRICT(DISTRICT_ID, DISTRICT_NAME, DISTRICT_TYPE, PROVINCE_ID) VALUES (?, ?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getDistrictId(), dto.getDistrictName(), dto.getDistrictType(), dto.getProvinceId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(DistrictDTO dto) {
      String sql = "UPDATE DISTRICT SET DISTRICT_NAME = ?, DISTRICT_TYPE = ?, PROVINCE_ID = ? WHERE DISTRICT_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getDistrictName(), dto.getDistrictType(), dto.getProvinceId(), dto.getDistrictId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(String id) {
      String sql = "DELETE FROM DISTRICT WHERE DISTRICT_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static DistrictDAO getInstance(){
      if (instance == null){
         instance = new DistrictDAO();
      }
      return instance;
   }

   private DistrictDAO(){
   }

   private static DistrictDAO instance = null;
}
