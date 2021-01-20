package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.DiscountEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class DiscountDAO implements IRetrieveEntity<DiscountEntity, Long>, IModifySingleEntityAutoIncrement<DiscountEntity, Long> {
   private static DiscountDAO instance = null;

   private DiscountDAO() {
   }

   public static DiscountDAO getInstance() {
      if (instance == null) {
         instance = new DiscountDAO();
      }
      return instance;
   }

   @Override
   public Long insert(DiscountEntity entity) {
      Long newId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newId = entity.getDiscountId();

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
   public boolean update(DiscountEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         DiscountEntity discountEntity = entityMgr.find(DiscountEntity.class, id);
         entityMgr.remove(discountEntity);

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
   public ArrayList<DiscountEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<DiscountEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM DiscountEntity u";
      TypedQuery<DiscountEntity> typedQuery = entityMgr.createQuery(query, DiscountEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<DiscountEntity> result = null;
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
   public DiscountEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(DiscountEntity.class, id);
   }

   @Override
   public Long count() {
      return EntityUtils.count(DiscountEntity.class.getName());
   }
}
