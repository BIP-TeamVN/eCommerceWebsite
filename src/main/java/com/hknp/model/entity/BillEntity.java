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

   public BillEntity() {
   }

   public List<BillDetailEntity> getBillDetailEntities() {
      return billDetailEntities;
   }

   public void setBillDetailEntities(List<BillDetailEntity> billDetailEntities) {
      this.billDetailEntities = billDetailEntities;
   }

   public Long getBillId() {
      return billId;
   }

   public void setBillId(Long billId) {
      this.billId = billId;
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
}
