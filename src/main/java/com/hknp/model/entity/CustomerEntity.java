package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity implements Serializable {
   @Id
   @Column(name = "USER_ID", columnDefinition = "BIGINT NOT NULL")
   Long userId;

   @Column(name = "REGISTER_DATE", columnDefinition = "DATE NULL")
   Date registerDate;

   @OneToOne(fetch = FetchType.EAGER)
   @PrimaryKeyJoinColumn(name = "USER_ID")
   UserEntity userEntity;

   public CustomerEntity () {}

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
}
