package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinceDTO {
   String provinceId;
   String provinceName;
   String provinceType;

   public ProvinceDTO(ResultSet resultSet) {
      try {
         provinceId = resultSet.getString("PROVINCE_ID");
         provinceName = resultSet.getString("PROVINCE_NAME");
         provinceType = resultSet.getString("PROVINCE_TYPE");
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
   }

   public ProvinceDTO(String provinceId, String provinceName, String provinceType) {
      this.provinceId = provinceId;
      this.provinceName = provinceName;
      this.provinceType = provinceType;
   }

   public String getProvinceId() {
      return provinceId;
   }

   public void setProvinceId(String provinceId) {
      this.provinceId = provinceId;
   }

   public String getProvinceName() {
      return provinceName;
   }

   public void setProvinceName(String provinceName) {
      this.provinceName = provinceName;
   }

   public String getProvinceType() {
      return provinceType;
   }

   public void setProvinceType(String provinceType) {
      this.provinceType = provinceType;
   }
}
