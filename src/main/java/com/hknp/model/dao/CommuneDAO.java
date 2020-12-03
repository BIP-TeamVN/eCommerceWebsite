package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.CommuneDTO;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommuneDAO implements IDatabaseAccess<String, CommuneDTO> {

   @Override
   public ArrayList<CommuneDTO> gets() {
      ArrayList<CommuneDTO> result = new ArrayList<>();

      String query = "SELECT * FROM COMMUNE;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }

      try {
         while (resultSet.next()) {
            CommuneDTO communeModel = new CommuneDTO(resultSet);
            result.add(communeModel);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public CommuneDTO getById(String id) {
      String query = "SELECT * FROM COMMUNE WHERE COMMUNE_ID = '" + id + "';";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new CommuneDTO(resultSet) : null;
   }

   @Override
   public int insert(CommuneDTO dto) {
      String sql = "INSERT INTO COMMUNE(COMMUNE_ID, COMMUNE_NAME, COMMUNE_TYPE, DISTRICT_ID) VALUES (?, ?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getCommuneId(), dto.getCommuneName(), dto.getCommuneType(), dto.getDistrictId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(CommuneDTO dto) {
      String sql = "UPDATE COMMUNE SET COMMUNE_NAME = ?, COMMUNE_TYPE = ?, DISTRICT_ID = ? WHERE COMMUNE_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getCommuneName(), dto.getCommuneType(), dto.getDistrictId(), dto.getCommuneId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(String id) {
      String sql = "DELETE FROM COMMUNE WHERE COMMUNE_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static CommuneDAO getInstance() {
      if (instance == null){
         instance = new CommuneDAO();
      }
      return instance;
   }

   private CommuneDAO () {
   }

   private static CommuneDAO instance = null;
}
