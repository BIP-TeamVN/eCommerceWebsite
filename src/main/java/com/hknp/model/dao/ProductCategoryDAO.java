package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.ProductCategoryEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
   public ArrayList<ProductCategoryEntity> gets() {
      return gets(null, null);
   }

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
   public Long count() {
      return EntityUtils.count(ProductCategoryEntity.class.getName());
   }

   public Long count(String keyword) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr;
      Query query;

      queryStr = "select count(*) from ProductCategoryEntity p " +
              "where p.productCategoryName like :keywordPara";
      query = entityMgr.createQuery(queryStr);

      query.setParameter("keywordPara", "%" + keyword + "%");

      return (Long) query.getSingleResult();
   }

   public ArrayList<ProductCategoryEntity> gets(Integer firstResult, Integer maxResults, String keyword, String columnName, String typeSort) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      Query query;
      String queryStr;

      queryStr = "select u from ProductCategoryEntity u " +
              "where u.productCategoryName like :keywordPara" + sortColumn(columnName, typeSort);

      query = entityMgr.createQuery(queryStr, ProductCategoryEntity.class);

      query.setParameter("keywordPara", "%" + keyword + "%");

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<ProductCategoryEntity> result = null;
      try {
         result = new ArrayList<>(query.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public String sortColumn(String columnName, String typeSort) {
      String result = "";
      if (!columnName.equals("")) {
         result = " ORDER BY u." + columnName + " " + typeSort;
      }
      return result;
   }
}
