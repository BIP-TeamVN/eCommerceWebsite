package com.hknp.model.dao;

import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.CommuneEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class CommuneDAO implements IRetrieveEntity<CommuneEntity, String> {
   private static CommuneDAO instance = null;

   private CommuneDAO() {
   }

   public static CommuneDAO getInstance() {
      if (instance == null) {
         instance = new CommuneDAO();
      }
      return instance;
   }

   public ArrayList<CommuneEntity> getByDistrictId(String districtId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT c FROM CommuneEntity c where c.districtId = :id";
      TypedQuery<CommuneEntity> typedQuery = entityMgr.createQuery(query, CommuneEntity.class)
              .setParameter("id", districtId);

      ArrayList<CommuneEntity> result = null;
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
   public ArrayList<CommuneEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<CommuneEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT c FROM CommuneEntity c";
      TypedQuery<CommuneEntity> typedQuery = entityMgr.createQuery(query, CommuneEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<CommuneEntity> result = null;
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
   public CommuneEntity getById(String id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(CommuneEntity.class, id);
   }

   @Override
   public Long count() {
      return EntityUtils.count(CommuneEntity.class.getName());
   }
}
