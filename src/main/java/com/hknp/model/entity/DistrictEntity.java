package com.hknp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISTRICT")
public class DistrictEntity {
   @Id
   @Column(name = "DISTRICT_ID")
   String districtId;

   @Column(name = "DISTRICT_NAME")
   String districtName;

   @Column(name = "DISTRICT_TYPE")
   String districtType;

   @Column(name = "PROVINCE_ID")
   String provinceId;

   public DistrictEntity(String districtId, String districtName, String districtType, String provinceId) {
      this.districtId = districtId;
      this.districtName = districtName;
      this.districtType = districtType;
      this.provinceId = provinceId;
   }

   public DistrictEntity() {

   }

   public String toJson() {
      return "{\"id\":\"" + districtId + "\",\"name\":\"" + getDistrictFullName() + "\"}";
   }

   public String getDistrictFullName() {
      switch (districtType) {
         case "C":
            return "Thành phố " + districtName;
         case "D":
            return "Quận " + districtName;
         case "T":
            return "Thị xã " + districtName;
         default:
            return "Huyện " + districtName;
      }
   }

   public String getDistrictId() {
      return districtId;
   }

   public void setDistrictId(String districtId) {
      this.districtId = districtId;
   }

   public String getDistrictName() {
      return districtName;
   }

   public void setDistrictName(String districtName) {
      this.districtName = districtName;
   }

   public String getDistrictType() {
      return districtType;
   }

   public void setDistrictType(String districtType) {
      this.districtType = districtType;
   }

   public String getProvinceId() {
      return provinceId;
   }

   public void setProvinceId(String provinceId) {
      this.provinceId = provinceId;
   }
}