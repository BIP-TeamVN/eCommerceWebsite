package com.hknp.model.entity;

import com.hknp.utils.DateTimeUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "USER_ID")
   Long userId;

   @Column(name = "LAST_NAME", columnDefinition = "NVARCHAR(40) NOT NULL")
   String lastName;

   @Column(name = "FIRST_NAME", columnDefinition = "NVARCHAR(10) NOT NULL")
   String firstName;

   @Column(name = "GENDER", columnDefinition = "NVARCHAR(5) NULL")
   String gender;

   @Column(name = "DATE_OF_BIRTH", columnDefinition = "DATE NULL")
   Date dateOfBirth;

   @Column(name = "SSN", columnDefinition = "VARCHAR(12) NULL")
   String ssn;

   @Column(name = "IMAGE", columnDefinition = "LONGTEXT DEFAULT NULL")
   String image;

   @Column(name = "PHONE_NUMBER", columnDefinition = "VARCHAR(15) NULL")
   String phoneNumber;

   @Column(name = "EMAIL", columnDefinition = "VARCHAR(40) NULL")
   String email;

   @Column(name = "USER_NAME", columnDefinition = "VARCHAR(40) NOT NULL")
   String userName;

   @Column(name = "PASSWORD", columnDefinition = "VARCHAR(32) NOT NULL")
   String password;

   @Column(name = "USER_TYPE", columnDefinition = "VARCHAR(15) DEFAULT 'CUSTOMER' NOT NULL")
   String userType;

   @Column(name = "STATUS", columnDefinition = "TINYINT(1) DEFAULT '1'")
   Boolean status;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "USER_ID")
   List<AddressEntity> addressEntities;

   public UserEntity() {

   }

   public UserEntity(String lastName, String firstName, String gender, Date dateOfBirth, String ssn, String phoneNumber, String email, String userName, String password, String userType) {
      this.userId = null;

      this.lastName = lastName;
      this.firstName = firstName;
      this.gender = gender;
      this.dateOfBirth = dateOfBirth;

      this.ssn = ssn;
      this.phoneNumber = phoneNumber;
      this.email = email;

      this.userName = userName;
      this.password = password;
      this.userType = userType;

      this.status = true;
   }

   public String toJson() {
      return "{" +
              "\"id\":\"" + getUserId() + "\"," +
              "\"username\":\"" + getUserName() + "\"," +
              "\"fullName\":\"" + getFullName() + "\"," +
              "\"phone\":\"" + getPhoneNumber() + "\"," +
              "\"email\":\"" + getEmail() + "\"," +
              "\"imgSrc\":\"" + getImageSrc() + "\"," +
              "\"status\":\"" + getStatus() + "\"" +
              "}";
   }

   public String getFullName() {
      return lastName + " " + firstName;
   }

   public String getDateOfBirthStr() {
      return getDateOfBirthStr("dd/MM/yyyy");
   }

   public String getDateOfBirthStr(String formatPattern) {
      return DateTimeUtils.dateToString(dateOfBirth, formatPattern);
   }

   public String getImageSrc() {
      if (image == null || image.isEmpty()) {
         if (gender.equals(Cons.User.USER_GENDER_FEMALE)) {
            return Cons.User.DEFAULT_USER_IMAGE_FEMALE_SRC;
         } else {
            return Cons.User.DEFAULT_USER_IMAGE_MALE_SRC;
         }
      } else {
         return image;
      }
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public Date getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public String getSsn() {
      return ssn;
   }

   public void setSsn(String ssn) {
      this.ssn = ssn;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUserType() {
      return userType;
   }

   public void setUserType(String userType) {
      this.userType = userType;
   }

   public Boolean getStatus() {
      return status;
   }

   public void setStatus(Boolean status) {
      this.status = status;
   }

   public List<AddressEntity> getAddressEntities() {
      return addressEntities;
   }

   public void setAddressEntities(List<AddressEntity> addressEntities) {
      this.addressEntities = addressEntities;
   }
}
