package com.hknp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROVINCE")
public class ProvinceEntity {
   @Id
   @Column(name = "PROVINCE_ID", columnDefinition = "VARCHAR(2) NOT NULL")
   String provinceId;

   @Column(name = "PROVINCE_NAME", columnDefinition = "NVARCHAR(20) NOT NULL")
   String provinceName;

   @Column(name = "PROVINCE_TYPE", columnDefinition = "VARCHAR(1) NOT NULL")
   String provinceType;

   public ProvinceEntity(String provinceId, String provinceName, String provinceType) {
      this.provinceId = provinceId;
      this.provinceName = provinceName;
      this.provinceType = provinceType;
   }

   public ProvinceEntity() {

   }

   public String toJson() {
      return "{\"id\":\"" + provinceId + "\",\"name\":\"" + getProvinceFullName() + "\"}";
   }

   public String getProvinceFullName() {
      return (provinceType.equals("C") ? "Thành phố " : "Tỉnh ") + provinceName;
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
