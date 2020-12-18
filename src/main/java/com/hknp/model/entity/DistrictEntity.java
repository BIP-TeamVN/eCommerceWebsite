package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "DISTRICT")
public class DistrictEntity implements Serializable {
   @Id
   @Column(name = "DISTRICT_ID")
   String districtId;
   @Column(name = "DISTRICT_NAME")
   String districtName;
   @Column(name = "DISTRICT_TYPE")
   String districtType;
   @Column(name = "PROVINCE_ID")
   String provinceId;

/*
   @ManyToOne(targetEntity = ProvinceEntity.class)
   @JoinColumn(name = "PROVINCE_ID", referencedColumnName = "PROVINCE_ID")
   ProvinceEntity province;
*/

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "districtEntity")
   @JoinColumn(name = "DISTRICT_ID")
   List<CommuneEntity> listCommune;

   public DistrictEntity(String districtId, String districtName, String districtType, String provinceId) {
      this.districtId = districtId;
      this.districtName = districtName;
      this.districtType = districtType;
      this.provinceId = provinceId;
   }

   public DistrictEntity() {

   }

   /*public ProvinceEntity getProvince() {
      return province;
   }

   public void setProvince(ProvinceEntity province) {
      this.province = province;
   }*/

   public List<CommuneEntity> getListCommune() {
      return listCommune;
   }

   public void setListCommune(List<CommuneEntity> listCommune) {
      this.listCommune = listCommune;
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
