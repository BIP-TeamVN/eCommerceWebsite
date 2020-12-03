package com.hknp.model.dao;

import com.hknp.interfaces.IDatabaseAccess;
import com.hknp.model.dto.AddressDTO;
import com.hknp.model.dto.ProvinceDTO;
import com.hknp.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressDAO implements IDatabaseAccess<Long, AddressDTO> {
   @Override
   public ArrayList<AddressDTO> gets() {
      ArrayList<AddressDTO> result = new ArrayList<>();

      String query = "SELECT * FROM ADDRESS;";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

      if (resultSet == null) {
         return result;
      }
      try {
         while (resultSet.next()) {
            AddressDTO addressModel = new AddressDTO(resultSet);
            result.add(addressModel);
         }
      }
      catch (SQLException exception) {
         exception.printStackTrace();
      }

      return result;
   }

   @Override
   public AddressDTO getById(Long id) {
      String query = "SELECT * FROM ADDRESS WHERE ADDRESS_ID = " + id + ";";
      ResultSet resultSet = DatabaseUtils.executeQuery(query, null);
      return resultSet != null ? new AddressDTO(resultSet) : null;
   }

   @Override
   public int insert(AddressDTO dto) {
      String sql = "INSERT INTO ADDRESS(ADDRESS_ID, STREET, COMMUNE_ID, DISTRICT_ID, PROVINCE_ID) VALUES (?, ?, ?, ?, ?);";
      List<Object> parameters = Arrays.asList(dto.getAddressId(), dto.getStreet(), dto.getCommuneId(), dto.getDistrictID(), dto.getProvinceId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int update(AddressDTO dto) {
      String sql = "UPDATE ADDRESS SET STREET = ?, COMMUNE_ID = ?, DISTRICT_ID = ?, PROVINCE_ID = ? WHERE ADDRESS_ID = ?";
      List<Object> parameters = Arrays.asList(dto.getStreet(), dto.getCommuneId(), dto.getDistrictID(), dto.getProvinceId(), dto.getAddressId());
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   @Override
   public int delete(Long id) {
      String sql = "DELETE FROM ADDRESS WHERE ADDRESS_ID = ?";
      List<Object> parameters = Arrays.asList(id);
      return DatabaseUtils.executeUpdate(sql, parameters);
   }

   public static AddressDAO getInstance(){
      if (instance == null) {
         instance = new AddressDAO();
      }
      return instance;
   }

   private AddressDAO(){
   }

   private static AddressDAO instance = null;
}
