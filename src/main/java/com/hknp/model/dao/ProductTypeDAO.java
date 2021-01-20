package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.ProductTypeEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ProductTypeDAO implements IRetrieveEntity<ProductTypeEntity, Long>, IModifySingleEntityAutoIncrement<ProductTypeEntity, Long> {
   private static ProductTypeDAO instance = null;

   private ProductTypeDAO() {
   }

   public static ProductTypeDAO getInstance() {
      if (instance == null) {
         instance = new ProductTypeDAO();
      }
      return instance;
   }

   @Override
   public Long insert(ProductTypeEntity entity) {
      Long newId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newId = entity.getProductTypeId();

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
   public boolean update(ProductTypeEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         ProductTypeEntity productTypeEntity = entityMgr.find(ProductTypeEntity.class, id);
         entityMgr.remove(productTypeEntity);

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
   public ArrayList<ProductTypeEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<ProductTypeEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM ProductTypeEntity u";
      TypedQuery<ProductTypeEntity> typedQuery = entityMgr.createQuery(query, ProductTypeEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<ProductTypeEntity> result = null;
      try {
         result = new ArrayList<>(typedQuery.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public ArrayList<ProductTypeEntity> getByProductId(Integer firstResult, Integer maxResults, Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM ProductTypeEntity u where u.productEntity.productId =" + id;
      TypedQuery<ProductTypeEntity> typedQuery = entityMgr.createQuery(query, ProductTypeEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<ProductTypeEntity> result = null;
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
   public ProductTypeEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(ProductTypeEntity.class, id);
   }

   @Override
   public Long count() {
      return EntityUtils.count(ProductTypeEntity.class.getName());
   }
}
