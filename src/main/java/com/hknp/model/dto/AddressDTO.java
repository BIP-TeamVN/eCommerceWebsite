package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDTO {
   Long addressId;
   String street;
   String communeId;
   String districtID;
   String provinceId;

   public AddressDTO(ResultSet resultSet) {
      try {
         addressId = resultSet.getLong("ADDRESS_ID");
         street = resultSet.getString("STREET");
         communeId = resultSet.getString("COMMUNE_ID");
         districtID = resultSet.getString("DISTRICT_ID");
         provinceId = resultSet.getString("PROVINCE_ID");
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
   }

   public AddressDTO(Long addressId, String street, String communeId, String districtID, String provinceId) {
      this.addressId = addressId;
      this.street = street;
      this.communeId = communeId;
      this.districtID = districtID;
      this.provinceId = provinceId;
   }

   public Long getAddressId() {
      return addressId;
   }

   public void setAddressId(Long addressId) {
      this.addressId = addressId;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public String getCommuneId() {
      return communeId;
   }

   public void setCommuneId(String communeId) {
      this.communeId = communeId;
   }

   public String getDistrictID() {
      return districtID;
   }

   public void setDistrictID(String districtID) {
      this.districtID = districtID;
   }

   public String getProvinceId() {
      return provinceId;
   }

   public void setProvinceId(String provinceId) {
      this.provinceId = provinceId;
   }
}
