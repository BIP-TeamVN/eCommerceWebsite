package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.BrandEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class BrandDAO implements IRetrieveEntity<BrandEntity, Long>, IModifySingleEntityAutoIncrement<BrandEntity, Long> {
   private static BrandDAO instance = null;

   private BrandDAO() {
   }

   public static BrandDAO getInstance() {
      if (instance == null) {
         instance = new BrandDAO();
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
      return gets(null, null);
   }

   @Override
   public ArrayList<BrandEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM BrandEntity u";
      TypedQuery<BrandEntity> typedQuery = entityMgr.createQuery(query, BrandEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

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
      return entityMgr.find(BrandEntity.class, id);
   }

   @Override
   public Long count() {
      return EntityUtils.count(BrandEntity.class.getName());
   }

   public Long count(String keyword) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr;
      Query query;

      queryStr = "select count(*) from BrandEntity p " +
              "where p.brandName like :keywordPara " +
              "or p.brandOrigin like :keywordPara ";
      query = entityMgr.createQuery(queryStr);

      query.setParameter("keywordPara", "%" + keyword + "%");

      return (Long) query.getSingleResult();
   }

   public ArrayList<BrandEntity> gets(Integer firstResult, Integer maxResults, String keyword, String columnName, String typeSort) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      Query query;
      String queryStr;

      queryStr = "select u from BrandEntity u " +
              "where u.brandName like :keywordPara " +
              "or u.brandOrigin like :keywordPara " + sortColumn(columnName, typeSort);

      query = entityMgr.createQuery(queryStr, BrandEntity.class);

      query.setParameter("keywordPara", "%" + keyword + "%");

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<BrandEntity> result = null;
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
