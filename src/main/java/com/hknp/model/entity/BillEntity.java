package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
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

   @Column(name = "BILL_CREATE_DATE")
   Date billCreateDate;

   @Column(name = "BILL_DONE_DATE")
   Date billDoneDate;

   public BillEntity() {
   }

   public Date getBillCreateDate() {
      return billCreateDate;
   }

   public void setBillCreateDate(Date billCreateDate) {
      this.billCreateDate = billCreateDate;
   }

   public Date getBillDoneDate() {
      return billDoneDate;
   }

   public void setBillDoneDate(Date billDoneDate) {
      this.billDoneDate = billDoneDate;
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

   public BigDecimal getTotal() {
      BigDecimal total = new BigDecimal(0);

      for (BillDetailEntity billdetail : billDetailEntities) {
         total = total.add(billdetail.getAmount());
      }
      //total = total.subtract(discountEntity.getDiscountMaxValue());
      return total;
   }

   public String toJson() {
      return "{" +
              "\"id\":\"" + billId + "\"," +
              "\"fullName\":\"" + addressEntity.getFullName() + "\"," +
              "\"phone\":\"" + addressEntity.phoneNumber + "\"," +
              "\"status\":\"" + status + "\"," +
              "\"fullAddress\":\"" + addressEntity.getStreet() + ", " +
              addressEntity.getCommuneEntity().getCommuneFullName() + ", " +
              addressEntity.getDistrictEntity().getDistrictFullName() + ", " +
              addressEntity.getProvinceEntity().getProvinceFullName() + "\"," +
              "\"total\":\"" + new DecimalFormat("###,###").format(getTotal()) + "\"" +
              "}";
   }
}
