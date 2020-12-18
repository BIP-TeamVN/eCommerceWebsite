package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntity;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.DistrictEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class DistrictDAO implements IRetrieveEntity<DistrictEntity, String>, IModifySingleEntity<DistrictEntity, String> {
   @Override
   public boolean insert(DistrictEntity entity) {
      return false;
   }

   @Override
   public boolean update(DistrictEntity entity) {
      return false;
   }

   @Override
   public boolean delete(String id) {
      return false;
   }

   @Override
   public ArrayList<DistrictEntity> gets() {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT p FROM DistrictEntity p";
      TypedQuery<DistrictEntity> typedQuery = entityMgr.createQuery(query, DistrictEntity.class);

      ArrayList<DistrictEntity> result = null;
      try {
         result = new ArrayList<>(typedQuery.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   @Override
   public DistrictEntity getById(String id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String hql = "SELECT p FROM DistrictEntity p WHERE p.provinceId = :id";
      TypedQuery<DistrictEntity> typedQuery = entityMgr.createQuery(hql, DistrictEntity.class);
      typedQuery.setParameter("id", id);

      DistrictEntity districtEntity = null;
      try {
         districtEntity = typedQuery.getSingleResult();
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return districtEntity;
   }
}
