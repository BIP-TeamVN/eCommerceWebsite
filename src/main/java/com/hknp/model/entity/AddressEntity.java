package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ADDRESS_ID")
   Long addressId;

   @OneToOne(fetch = FetchType.LAZY, mappedBy = "UserAddressEntity", cascade = CascadeType.ALL)
   private UserAddressEntity userAddressEntity;

   @Column(name = "STREET")
   String street;

   @Column(name = "COMMUNE_ID")
   String communeId;

   @Column(name = "DISTRICT_ID")
   String districtId;

   @Column(name = "PROVINCE_ID")
   String provinceId;

   public Long getAddressId() {
      return addressId;
   }

   public void setAddressId(Long addressId) {
      this.addressId = addressId;
   }

   public UserAddressEntity getUserAddressEntity() {
      return userAddressEntity;
   }

   public void setUserAddressEntity(UserAddressEntity userAddressEntity) {
      this.userAddressEntity = userAddressEntity;
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

   public String getDistrictId() {
      return districtId;
   }

   public void setDistrictId(String districtId) {
      this.districtId = districtId;
   }

   public String getProvinceId() {
      return provinceId;
   }

   public void setProvinceId(String provinceId) {
      this.provinceId = provinceId;
   }

   public AddressEntity(Long addressId, String street, String communeId, String districtId, String provinceId) {
      this.addressId = addressId;
      this.street = street;
      this.communeId = communeId;
      this.districtId = districtId;
      this.provinceId = provinceId;
   }

   public AddressEntity() {

   }
}
