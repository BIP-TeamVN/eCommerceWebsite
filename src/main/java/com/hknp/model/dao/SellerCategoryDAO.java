package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.SellerCategoryEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class SellerCategoryDAO implements IRetrieveEntity<SellerCategoryEntity, Long>, IModifySingleEntityAutoIncrement<SellerCategoryEntity, Long> {
   private static SellerCategoryDAO instance = null;

   private SellerCategoryDAO() {
   }

   public static SellerCategoryDAO getInstance() {
      if (instance == null) {
         instance = new SellerCategoryDAO();
      }
      return instance;
   }

   @Override
   public Long insert(SellerCategoryEntity entity) {
      Long newSellerCategoryId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newSellerCategoryId = entity.getSellerCategoryId();

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
      } finally {
         entityMgr.close();
      }

      return newSellerCategoryId;
   }

   @Override
   public boolean update(SellerCategoryEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         SellerCategoryEntity sellerCategoryEntity = entityMgr.find(SellerCategoryEntity.class, id);
         entityMgr.remove(sellerCategoryEntity);

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
   public ArrayList<SellerCategoryEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<SellerCategoryEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT sc FROM SellerCategoryEntity sc";
      TypedQuery<SellerCategoryEntity> typedQuery = entityMgr.createQuery(query, SellerCategoryEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<SellerCategoryEntity> result = null;
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
   public SellerCategoryEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(SellerCategoryEntity.class, id);
   }

   @Override
   public Long count() {return EntityUtils.count(SellerCategoryEntity.class.getName());}
}
