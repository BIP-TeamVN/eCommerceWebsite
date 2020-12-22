package com.hknp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "commune")
public class CommuneEntity {
   @Id
   @Column(name = "COMMUNE_ID")
   String communeId;

   @Column(name = "COMMUNE_NAME")
   String communeName;

   @Column(name = "COMMUNE_TYPE")
   String communeType;

   @Column(name = "DISTRICT_ID")
   String districtId;

   public CommuneEntity(String communeId, String communeName, String communeType, String districtId) {
      this.communeId = communeId;
      this.communeName = communeName;
      this.communeType = communeType;
      this.districtId = districtId;
   }

   public CommuneEntity() {
   }

   public String toJson() {
      return "{\"id\":\"" + communeId + "\",\"name\":\"" + getCommuneFullName() + "\"}";
   }

   public String getCommuneFullName() {
      switch (communeType) {
         case "W":
            return "Phường " + communeName;
         case "T":
            return "Thị trấn " + communeName;
         default:
            return "Xã " + communeName;
      }
   }

   public String getCommuneId() {
      return communeId;
   }

   public void setCommuneId(String communeId) {
      this.communeId = communeId;
   }

   public String getCommuneName() {
      return communeName;
   }

   public void setCommuneName(String communeName) {
      this.communeName = communeName;
   }

   public String getCommuneType() {
      return communeType;
   }

   public void setCommuneType(String communeType) {
      this.communeType = communeType;
   }

   public String getDistrictId() {
      return districtId;
   }

   public void setDistrictId(String districtId) {
      this.districtId = districtId;
   }
}