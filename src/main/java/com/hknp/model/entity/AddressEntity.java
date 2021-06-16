package com.hknp.model.entity;

import com.hknp.model.dao.CommuneDAO;
import com.hknp.model.dao.DistrictDAO;
import com.hknp.model.dao.ProvinceDAO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ADDRESS_ID")
   Long addressId;

   @Column(name = "STREET")
   String street;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "COMMUNE_ID")
   CommuneEntity communeEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "DISTRICT_ID")
   DistrictEntity districtEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PROVINCE_ID")
   ProvinceEntity provinceEntity;

   @Column(name = "USER_ID")
   Long userId;

   @Column(name = "FULL_NAME")
   String fullName;

   @Column(name = "ADDRESS_NAME")
   String addressName;

   @Column(name = "PHONE_NUMBER")
   String phoneNumber;

   public AddressEntity(String street, String communeId, String districtId, String provinceId, Long userId, String fullName, String addressName, String phoneNumber) {
      this.addressId = null;

      this.street = street;
      this.communeEntity = CommuneDAO.getInstance().getById(communeId);
      this.districtEntity = DistrictDAO.getInstance().getById(districtId);
      this.provinceEntity = ProvinceDAO.getInstance().getById(provinceId);

      this.userId = userId;
      this.fullName = fullName;
      this.addressName = addressName;
      this.phoneNumber = phoneNumber;
   }

   public AddressEntity() {

   }

   public DistrictEntity getDistrictEntity() {
      return districtEntity;
   }

   public void setDistrictEntity(DistrictEntity districtEntity) {
      this.districtEntity = districtEntity;
   }

   public CommuneEntity getCommuneEntity() {
      return communeEntity;
   }

   public void setCommuneEntity(CommuneEntity communeEntity) {
      this.communeEntity = communeEntity;
   }

   public ProvinceEntity getProvinceEntity() {
      return provinceEntity;
   }

   public void setProvinceEntity(ProvinceEntity provinceEntity) {
      this.provinceEntity = provinceEntity;
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

   public String toJson1() {
      String fullAddress = street + ", " +
              communeEntity.getCommuneFullName() + ", " +
              districtEntity.getDistrictFullName() + ", " +
              provinceEntity.getProvinceFullName();
      return "{" +
              "\"addressId\":\"" + addressId + "\"," +
              "\"fullAddress\":\"" + fullAddress + "\"," +
              "\"userId\":\"" + userId + "\"," +
              "\"fullName\":\"" + fullName + "\"," +
              "\"addressName\":\"" + addressName + "\"," +
              "\"phoneNumber\":\"" + phoneNumber + "\"" +
              "}";
   }

   public String toJson() {
      return "{" +
              "\"addressId\":\"" + addressId + "\"," +
              "\"street\":\"" + street + "\"," +
              "\"province\":\"" + provinceEntity.toJson() + "\"," +
              "\"district\":\"" + districtEntity.toJson() + "\"," +
              "\"commune\":\"" + communeEntity.toJson() + "\"," +
              "\"userId\":\"" + userId + "\"," +
              "\"fullName\":\"" + fullName + "\"," +
              "\"addressName\":\"" + addressName + "\"," +
              "\"phoneNumber\":\"" + phoneNumber + "\"" +
              "}";
   }
}
