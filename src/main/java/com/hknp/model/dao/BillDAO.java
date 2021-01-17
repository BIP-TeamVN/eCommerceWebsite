package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.BillDetailEntity;
import com.hknp.model.entity.BillEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.ArrayList;

public class BillDAO implements IRetrieveEntity<BillEntity, Long>, IModifySingleEntityAutoIncrement<BillEntity, Long> {
   private static BillDAO instance = null;

   private BillDAO() {
   }

   public static BillDAO getInstance() {
      if (instance == null) {
         instance = new BillDAO();
      }
      return instance;
   }

   @Override
   public Long insert(BillEntity entity) {
      Long newId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newId = entity.getBillId();

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
   public boolean update(BillEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         BillEntity billEntity = entityMgr.find(BillEntity.class, id);
         entityMgr.remove(billEntity);

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
   public ArrayList<BillEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<BillEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM BillEntity u";
      TypedQuery<BillEntity> typedQuery = entityMgr.createQuery(query, BillEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<BillEntity> result = null;
      try {
         result = new ArrayList<>(typedQuery.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public ArrayList<BillEntity> gets(Integer firstResult, Integer maxResults, Integer status) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM BillEntity u where u.deliveryEntity.id = null and u.status = " + status;
      TypedQuery<BillEntity> typedQuery = entityMgr.createQuery(query, BillEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<BillEntity> result = null;
      try {
         result = new ArrayList<>(typedQuery.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public ArrayList<BillEntity> getsForDelivery(Integer firstResult, Integer maxResults, Long deliveryId, Integer status) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String strQuery = "SELECT u FROM BillEntity u where u.status = :status " +
              "and u.deliveryEntity.id = :deliveryId "  ;

      Query query = entityMgr.createQuery(strQuery);
      query.setParameter("deliveryId", deliveryId);
      query.setParameter("status", status);

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<BillEntity> result = null;
      try {
         result = new ArrayList<>(query.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public ArrayList<BillEntity> getsForSeller(Integer firstResult, Integer maxResults, Long sellerId, Integer status) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String strQuery = "SELECT distinct u.billEntity FROM BillDetailEntity AS u " +
              "WHERE u.productTypeEntity.productEntity.sellerEntity.userId = :sellerId " +
              "and u.billEntity.status = :status";

      Query query = entityMgr.createQuery(strQuery);
      query.setParameter("sellerId", sellerId);
      query.setParameter("status", status);

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<BillEntity> result = null;
      try {
         result = new ArrayList<>(query.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   @Override
   public BillEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(BillEntity.class, id);
   }

   @Override
   public Long count() {return EntityUtils.count(BillEntity.class.getName());}
}