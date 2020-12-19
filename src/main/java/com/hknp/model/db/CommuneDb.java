package com.hknp.model.db;

import com.hknp.interfaces.IModifySingleEntity;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.CommuneEntity;
import com.hknp.model.entity.ProvinceEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class CommuneDb implements IRetrieveEntity<CommuneEntity, String>, IModifySingleEntity<CommuneEntity, String> {

   private static CommuneDb instance = null;

   private CommuneDb() {
   }

   public static CommuneDb getInstance() {
      if (instance == null) {
         instance = new CommuneDb();
      }
      return instance;
   }
   @Override
   public boolean insert(CommuneEntity entity) {
      return false;
   }

   @Override
   public boolean update(CommuneEntity entity) {
      return false;
   }

   @Override
   public boolean delete(String id) {
      return false;
   }

   @Override
   public ArrayList<CommuneEntity> gets() {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT p FROM CommuneEntity p";
      TypedQuery<CommuneEntity> typedQuery = entityMgr.createQuery(query, CommuneEntity.class);

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

      String hql = "SELECT p FROM CommuneEntity p WHERE p.communeId = :id";
      TypedQuery<CommuneEntity> typedQuery = entityMgr.createQuery(hql, CommuneEntity.class);
      typedQuery.setParameter("id", id);

      CommuneEntity communeEntity = null;
      try {
         communeEntity = typedQuery.getSingleResult();
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return communeEntity;
   }
}
