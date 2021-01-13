package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "BILL")
public class BillEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "BILL_ID")
   Long billId;

   @Column(name = "BILL_STATUS")
   Integer status;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "CUSTOMER_ID")
   CustomerEntity customerEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ADDRESS_ID")
   AddressEntity addressEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "DISCOUNT_ID")
   DiscountEntity discountEntity;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "BILL_ID")
   List<BillDetailEntity> billDetailEntities;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "DELIVERY_ID")
   DeliveryEntity deliveryEntity;

   public BillEntity() {
   }

   public Long getBillId() {
      return billId;
   }

   public void setBillId(Long billId) {
      this.billId = billId;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public CustomerEntity getCustomerEntity() {
      return customerEntity;
   }

   public void setCustomerEntity(CustomerEntity customerEntity) {
      this.customerEntity = customerEntity;
   }

   public AddressEntity getAddressEntity() {
      return addressEntity;
   }

   public void setAddressEntity(AddressEntity addressEntity) {
      this.addressEntity = addressEntity;
   }

   public DiscountEntity getDiscountEntity() {
      return discountEntity;
   }

   public void setDiscountEntity(DiscountEntity discountEntity) {
      this.discountEntity = discountEntity;
   }

   public List<BillDetailEntity> getBillDetailEntities() {
      return billDetailEntities;
   }

   public void setBillDetailEntities(List<BillDetailEntity> billDetailEntities) {
      this.billDetailEntities = billDetailEntities;
   }

   public DeliveryEntity getDeliveryEntity() {
      return deliveryEntity;
   }

   public void setDeliveryEntity(DeliveryEntity deliveryEntity) {
      this.deliveryEntity = deliveryEntity;
   }

   public String toJson() {
      return "{" +
              "\"id\":\"" + billId + "\"," +
              "\"fullName\":\"" + addressEntity.getFullName() + "\"," +
              "\"phone\":\"" + addressEntity.phoneNumber + "\"," +
              "\"fullAddress\":\"" + addressEntity.getStreet() +", " +
              addressEntity.getCommuneEntity().getCommuneFullName() + ", " +
              addressEntity.getDistrictEntity().getDistrictFullName() + ", " +
              addressEntity.getProvinceEntity().getProvinceFullName() + "\"" +
              "}";
   }
}
