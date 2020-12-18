package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.ProductSubCategoryEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ProductSubCategoryDAO implements IRetrieveEntity<ProductSubCategoryEntity, Long>, IModifySingleEntityAutoIncrement<ProductSubCategoryEntity, Long> {
   private static ProductSubCategoryDAO instance = null;

   private ProductSubCategoryDAO() {
   }

   public static ProductSubCategoryDAO getInstance() {
      if (instance == null) {
         instance = new ProductSubCategoryDAO();
      }
      return instance;
   }

   @Override
   public Long insert(ProductSubCategoryEntity entity) {
      Long newId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newId = entity.getProductSubCategoryId();

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
   public boolean update(ProductSubCategoryEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         ProductSubCategoryEntity productSubCategoryEntity = entityMgr.find(ProductSubCategoryEntity.class, id);
         entityMgr.remove(productSubCategoryEntity);

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
   public ArrayList<ProductSubCategoryEntity> gets() {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM ProductSubCategoryEntity u";
      TypedQuery<ProductSubCategoryEntity> typedQuery = entityMgr.createQuery(query, ProductSubCategoryEntity.class);

      ArrayList<ProductSubCategoryEntity> result = null;
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
   public ProductSubCategoryEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      ProductSubCategoryEntity productSubCategoryEntity  = entityMgr.find(ProductSubCategoryEntity.class, id);
      return productSubCategoryEntity;
   }
}
