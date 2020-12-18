package com.hknp.model.entity;

import com.hknp.utils.EntityUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "COMMUNE")
public class CommuneEntity implements Serializable {
   @Id
   @Column(name = "COMMUNE_ID")
   String communeId;
   @Column(name = "COMMUNE_NAME")
   String communeName;
   @Column(name = "COMMUNE_TYPE")
   String communeType;
   @Column(name = "DISTRICT_ID")
   String districtId;

   /*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "DISTRICT_ID")
   DistrictEntity district;*/

   public CommuneEntity(String communeId, String communeName, String communeType, String districtId) {
      this.communeId = communeId;
      this.communeName = communeName;
      this.communeType = communeType;
      this.districtId = districtId;
   }

   public CommuneEntity() {

   }

   public String getCommuneId() {
      return communeId;
   }

   public void setCommuneId(String communeId) {
      this.communeId = communeId;
   }

   public String getCommuneName() {
      return communeName;
   }

   public void setCommuneName(String communeName) {
      this.communeName = communeName;
   }

   public String getCommuneType() {
      return communeType;
   }

   public void setCommuneType(String communeType) {
      this.communeType = communeType;
   }

   public String getDistrictId() {
      return districtId;
   }

   public void setDistrictId(String districtId) {
      this.districtId = districtId;
   }

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressEntity")
   private Collection<AddressEntity> communeEntity;

   public Collection<AddressEntity> getCommuneEntity() {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT p FROM AddressEntity p";
      TypedQuery<AddressEntity> typedQuery = entityMgr.createQuery(query, AddressEntity.class);

      ArrayList<AddressEntity> result = null;
      try {
         result = new ArrayList<>(typedQuery.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public void setCommuneEntity(Collection<AddressEntity> communeEntity) {
      this.communeEntity = communeEntity;
   }

   @ManyToOne(optional = false)
   private DistrictEntity districtEntity;

   public DistrictEntity getDistrictEntity() {
      return districtEntity;
   }

   public void setDistrictEntity(DistrictEntity districtEntity) {
      this.districtEntity = districtEntity;
   }
}
