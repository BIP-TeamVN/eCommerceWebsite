package com.hknp.model.entity;

import com.hknp.utils.DateTimeUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity implements Serializable {
   @Id
   @Column(name = "USER_ID", columnDefinition = "BIGINT NOT NULL")
   Long userId;

   @Column(name = "REGISTER_DATE", columnDefinition = "DATE NULL")
   Date registerDate;

   @OneToOne(fetch = FetchType.LAZY)
   @PrimaryKeyJoinColumn(name = "USER_ID")
   UserEntity userEntity;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "CUSTOMER_ID")
   List<BillEntity> billEntities;

   public CustomerEntity() {
   }

   public List<BillEntity> getBillEntities() {
      return billEntities;
   }

   public void setBillEntities(List<BillEntity> billEntities) {
      this.billEntities = billEntities;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Date getRegisterDate() {
      return registerDate;
   }

   public void setRegisterDate(Date registerDate) {
      this.registerDate = registerDate;
   }

   public UserEntity getUserEntity() {
      return userEntity;
   }

   public void setUserEntity(UserEntity userEntity) {
      this.userEntity = userEntity;
   }

   public String toJson() {
      return "{" +
              "\"id\":\"" + userId + "\"," +
              "\"fullName\":\"" + userEntity.getFullName() + "\"," +
              "\"gender\":\"" + userEntity.getGender() + "\"," +
              "\"dob\":\"" + userEntity.getDateOfBirthStr() + "\"," +
              "\"phone\":\"" + userEntity.getPhoneNumber() + "\"," +
              "\"email\":\"" + userEntity.getEmail() + "\"," +
              "\"registerDate\":\"" + DateTimeUtils.dateToString(registerDate, "dd/MM/yyyy") + "\"," +
              "\"imgSrc\":\"" + userEntity.getImageSrc() + "\"," +
              "\"status\":\"" + userEntity.getStatus() + "\"" +
              "}";
   }
}
