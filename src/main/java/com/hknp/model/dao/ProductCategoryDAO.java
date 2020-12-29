package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.DiscountEntity;
import com.hknp.model.entity.ProductCategoryEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ProductCategoryDAO implements IRetrieveEntity<ProductCategoryEntity, Long>, IModifySingleEntityAutoIncrement<ProductCategoryEntity, Long> {
   private static ProductCategoryDAO instance = null;

   private ProductCategoryDAO() {
   }

   public static ProductCategoryDAO getInstance() {
      if (instance == null) {
         instance = new ProductCategoryDAO();
      }
      return instance;
   }

   @Override
   public Long insert(ProductCategoryEntity entity) {
      Long newProductCategory = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newProductCategory = entity.getProductCategoryId();

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
      } finally {
         entityMgr.close();
      }

      return newProductCategory;
   }

   @Override
   public boolean update(ProductCategoryEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         ProductCategoryEntity productCategoryEntity = entityMgr.find(ProductCategoryEntity.class, id);
         entityMgr.remove(productCategoryEntity);

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
   public ArrayList<ProductCategoryEntity> gets() { return gets(null, null); }

   @Override
   public ArrayList<ProductCategoryEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM ProductCategoryEntity u";
      TypedQuery<ProductCategoryEntity> typedQuery = entityMgr.createQuery(query, ProductCategoryEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<ProductCategoryEntity> result = null;
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
   public ProductCategoryEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(ProductCategoryEntity.class, id);
   }


   @Override
   public Long count() {return EntityUtils.count(ProductCategoryEntity.class.getName());}
}
