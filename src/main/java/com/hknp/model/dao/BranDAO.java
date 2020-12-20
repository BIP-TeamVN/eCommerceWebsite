package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.BillEntity;
import com.hknp.model.entity.BrandEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class BranDAO implements IRetrieveEntity<BrandEntity, Long>, IModifySingleEntityAutoIncrement<BrandEntity, Long> {
   private static BranDAO instance = null;

   private BranDAO() {
   }

   public static BranDAO getInstance() {
      if (instance == null) {
         instance = new BranDAO();
      }
      return instance;
   }

   @Override
   public Long insert(BrandEntity entity) {
      Long newId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newId = entity.getBrandId();

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
      } finally {
         entityMgr.close();
      }

      return newId;
   }

   @Override
   public boolean update(BrandEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         BrandEntity brandEntity = entityMgr.find(BrandEntity.class, id);
         entityMgr.remove(brandEntity);

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
         entityMgr.close();
         return false;
      }
      return true;
   }
   @Override
   public ArrayList<BrandEntity> gets() {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM BrandEntity u";
      TypedQuery<BrandEntity> typedQuery = entityMgr.createQuery(query, BrandEntity.class);

      ArrayList<BrandEntity> result = null;
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
   public BrandEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      BrandEntity brandEntity = entityMgr.find(BrandEntity.class, id);
      return brandEntity;
   }
}
