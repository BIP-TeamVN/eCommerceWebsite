package com.hknp.model.entity;

import com.hknp.utils.DateTimeUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity implements Serializable {
   @Id
   @Column(name = "USER_ID", columnDefinition = "BIGINT NOT NULL")
   Long userId;

   @Column(name = "START_DATE", columnDefinition = "DATE NULL")
   Date startDate;

   @Column(name = "SALARY", columnDefinition = "DECIMAL(19, 2) NULL")
   BigDecimal salary;

   @OneToOne(fetch = FetchType.LAZY)
   @PrimaryKeyJoinColumn(name = "USER_ID")
   UserEntity userEntity;

   public EmployeeEntity() {

   }

   public String toJson() {
      return "{" +
              "\"id\":\"" + userId + "\"," +
              "\"fullName\":\"" + userEntity.getFullName() + "\"," +
              "\"gender\":\"" + userEntity.getGender() + "\"," +
              "\"dob\":\"" + userEntity.getDateOfBirthStr() + "\"," +
              "\"phone\":\"" + userEntity.getPhoneNumber() + "\"," +
              "\"email\":\"" + userEntity.getEmail() + "\"," +
              "\"imgSrc\":\"" + userEntity.getImageSrc() + "\"," +
              "\"salary\":\"" + salary + "\"," +
              "\"startDate\":\"" + getStartDateStr("dd/MM/yyyy") + "\"," +
              "\"status\":\"" + userEntity.getStatus() + "\"" +
              "}";
   }

   public String getStartDateStr(String formatPattern) {
      return DateTimeUtils.dateToString(startDate, formatPattern);
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public BigDecimal getSalary() {
      return salary;
   }

   public void setSalary(BigDecimal salary) {
      this.salary = salary;
   }

   public UserEntity getUserEntity() {
      return userEntity;
   }

   public void setUserEntity(UserEntity userEntity) {
      this.userEntity = userEntity;
   }
}
