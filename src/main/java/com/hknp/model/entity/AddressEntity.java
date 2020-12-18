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

   @Column(name = "STREET")
   String street;

   @Column(name = "COMMUNE_ID")
   String communeId;

   @Column(name = "DISTRICT_ID")
   String districtId;

   @Column(name = "PROVINCE_ID")
   String provinceId;

   @Column(name = "USER_ID")
   Long userId;

   @Column(name = "FULL_NAME")
   String fullName;

   @Column(name = "ADDRESS_NAME")
   String addressName;

   @Column(name = "PHONE_NUMBER")
   String phoneNumber;

   public AddressEntity(Long addressId, String street, String communeId, String districtId, String provinceId, Long userId, String fullName, String addressName, String phoneNumber) {
      this.addressId = addressId;
      this.street = street;
      this.communeId = communeId;
      this.districtId = districtId;
      this.provinceId = provinceId;
      this.userId = userId;
      this.fullName = fullName;
      this.addressName = addressName;
      this.phoneNumber = phoneNumber;
   }
   public AddressEntity() {

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

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getAddressName() {
      return addressName;
   }

   public void setAddressName(String addressName) {
      this.addressName = addressName;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
   /* @ManyToOne
   @JoinColumn(name = "PROVINCE_ID")
   ProvinceEntity province;*/
   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   private CommuneEntity addressEntity;

   public CommuneEntity getAddressEntity() {
      return addressEntity;
   }

   public void setAddressEntity(CommuneEntity addressEntity) {
      this.addressEntity = addressEntity;
   }
}
