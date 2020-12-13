package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PROVINCE")
public class ProvinceEntity implements Serializable {
   @Id
   @Column(name = "PROVINCE_ID", columnDefinition = "VARCHAR(2) NOT NULL")
   String provinceId;

   @Column(name = "PROVINCE_NAME", columnDefinition = "NVARCHAR(20) NOT NULL")
   String provinceName;

   @Column(name = "PROVINCE_TYPE", columnDefinition = "VARCHAR(1) NOT NULL")
   String provinceType;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "PROVINCE_ID")
   List<DistrictEntity> listDistrict;

   public ProvinceEntity() {

   }

   public List<DistrictEntity> getListDistrict() {
      return listDistrict;
   }

   public void setListDistrict(List<DistrictEntity> listDistrict) {
      this.listDistrict = listDistrict;
   }

   public ProvinceEntity(String provinceId, String provinceName, String provinceType) {
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
