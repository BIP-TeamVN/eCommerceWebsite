package com.hknp.model.dao;

import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.DistrictEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class DistrictDAO implements IRetrieveEntity<DistrictEntity, String> {
   private static DistrictDAO instance = null;

   private DistrictDAO() {
   }

   public static DistrictDAO getInstance() {
      if (instance == null) {
         instance = new DistrictDAO();
      }
      return instance;
   }

   public ArrayList<DistrictEntity> getByProvinceId(String provinceId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT d FROM DistrictEntity d where d.provinceId =:id";
      TypedQuery<DistrictEntity> typedQuery = entityMgr.createQuery(query, DistrictEntity.class)
              .setParameter("id", provinceId);

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
   public ArrayList<DistrictEntity> gets() {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT d FROM DistrictEntity d";
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
      DistrictEntity result = entityMgr.find(DistrictEntity.class, id);
      return result;
   }
}
