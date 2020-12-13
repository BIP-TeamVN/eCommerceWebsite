package com.hknp.model.db;

import com.hknp.interfaces.IModifySingleEntity;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.ProvinceEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ProvinceDB implements IRetrieveEntity<ProvinceEntity, String>, IModifySingleEntity<ProvinceEntity, String> {
   private static ProvinceDB instance = null;

   private ProvinceDB() {
   }

   public static ProvinceDB getInstance() {
      if (instance == null) {
         instance = new ProvinceDB();
      }
      return instance;
   }

   @Override
   public ArrayList<ProvinceEntity> gets() {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT p FROM ProvinceEntity p";
      TypedQuery<ProvinceEntity> typedQuery = entityMgr.createQuery(query, ProvinceEntity.class);

      ArrayList<ProvinceEntity> result = null;
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
   public ProvinceEntity getById(String id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String hql = "SELECT p FROM ProvinceEntity p WHERE p.provinceId = :id";
      TypedQuery<ProvinceEntity> typedQuery = entityMgr.createQuery(hql, ProvinceEntity.class);
      typedQuery.setParameter("id", id);

      ProvinceEntity province = null;
      try {
         province = typedQuery.getSingleResult();
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return province;
   }

   @Override
   public boolean insert(ProvinceEntity entity) {
      boolean isSucceed = true;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
         isSucceed = false;
      } finally {
         entityMgr.close();
      }

      return isSucceed;
   }

   @Override
   public boolean update(ProvinceEntity entity) {
      return EntityUtils.merge(entity);
      /*boolean isSucceed = true;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.merge(entity);

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
         isSucceed = false;
      } finally {
         entityMgr.close();
      }

      return isSucceed;*/
   }

   @Override
   public boolean delete(String id) {
      boolean isSucceed = true;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = entityMgr.getTransaction();;

      String hql = "DELETE FROM ProvinceEntity p WHERE p.provinceId = :id";
      Query query = entityMgr.createQuery(hql).setParameter("id", id);

      try {
         entityTrans.begin();

         isSucceed = query.executeUpdate() > 0;

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
         isSucceed = false;
      } finally {
         entityMgr.close();
      }

      return isSucceed;

      /*boolean isSucceed = true;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         ProvinceEntity entity = entityMgr.find(ProvinceEntity.class, id);
         entityMgr.remove(entity);

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
         isSucceed = false;
      } finally {
         entityMgr.close();
      }

      return isSucceed;*/


/*
      boolean isSucceed = false;
      EntityManager entityMgr = EntityUtils.getEntityManager();

      try {
         isSucceed = entityMgr.createQuery("DELETE FROM ProvinceEntity p WHERE p.provinceId = :id")
                 .setParameter("id", id)
                 .executeUpdate() > 0;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }

      return isSucceed;*/
   }
}
