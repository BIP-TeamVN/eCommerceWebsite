package com.hknp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER_ADDRESS")
public class UserAddressEntity {
   @Id
   @Column(name = "USER_ID", columnDefinition = "BIGINT NOT NULL")
   Long userId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "USER_ID", nullable = false, foreignKey = @ForeignKey(name = "fk_UserAddress_User"))
   private UserEntity userEntity;

   @Id
   @Column(name = "ADDRESS_ID", columnDefinition = "BIGINT NOT NULL")
   Long addressId;

   @OneToOne(fetch = FetchType.LAZY)
   @PrimaryKeyJoinColumn(name = "ADDRESS_ID", foreignKey = @ForeignKey(name = "fk_UserAddress_Address"))
   private AddressEntity addressEntity;

   @Column(name = "FULL_NAME", columnDefinition = "NVARCHAR(50) NULL")
   Long fullName;

   @Column(name = "PHONE_NUMBER", columnDefinition = "NVARCHAR(15) NULL")
   Long phoneNumber;

   @Column(name = "ADDRESS_NAME", columnDefinition = "NVARCHAR(20) NULL")
   Long addressName;

   public UserAddressEntity(Long userId, UserEntity userEntity, Long addressId, AddressEntity addressEntity, Long fullName, Long phoneNumber, Long addressName) {
      this.userId = userId;
      this.userEntity = userEntity;
      this.addressId = addressId;
      this.addressEntity = addressEntity;
      this.fullName = fullName;
      this.phoneNumber = phoneNumber;
      this.addressName = addressName;
   }

   public UserAddressEntity () {

   }
}
