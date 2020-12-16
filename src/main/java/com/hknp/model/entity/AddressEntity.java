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

  /* @ManyToOne
   @JoinColumn(name = "PROVINCE_ID")
   ProvinceEntity province;*/
}
