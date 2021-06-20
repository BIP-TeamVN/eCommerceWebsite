package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.BillDetailEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class BillDetailDAO implements IRetrieveEntity<BillDetailEntity, Long>, IModifySingleEntityAutoIncrement<BillDetailEntity, Long> {
   private static BillDetailDAO instance = null;

   private BillDetailDAO() {
   }

   public static BillDetailDAO getInstance() {
      if (instance == null) {
         instance = new BillDetailDAO();
      }
      return instance;
   }

   @Override
   public Long insert(BillDetailEntity entity) {
      Long newId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newId = entity.getBillDetailId();

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
   public boolean update(BillDetailEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         BillDetailEntity billDetailEntity = entityMgr.find(BillDetailEntity.class, id);
         entityMgr.remove(billDetailEntity);

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
   public ArrayList<BillDetailEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<BillDetailEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM BillDetailEntity u";
      TypedQuery<BillDetailEntity> typedQuery = entityMgr.createQuery(query, BillDetailEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<BillDetailEntity> result = null;
      try {
         result = new ArrayList<>(typedQuery.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public ArrayList<BillDetailEntity> gets(Integer firstResult, Integer maxResults, Long billId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM BillDetailEntity u where u.billEntity.id =" + billId;
      TypedQuery<BillDetailEntity> typedQuery = entityMgr.createQuery(query, BillDetailEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<BillDetailEntity> result = null;
      try {
         result = new ArrayList<>(typedQuery.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public ArrayList<BillDetailEntity> gets(Integer firstResult, Integer maxResults, Long billId, Long userId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM BillDetailEntity u where u.billEntity.id = :billId AND u.billEntity.customerEntity.id = :userId";
      TypedQuery<BillDetailEntity> typedQuery = entityMgr.createQuery(query, BillDetailEntity.class);

      typedQuery.setParameter("billId", billId);
      typedQuery.setParameter("userId", userId);
      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<BillDetailEntity> result = null;
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
   public BillDetailEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(BillDetailEntity.class, id);
   }

   @Override
   public Long count() {
      return EntityUtils.count(BillDetailEntity.class.getName());
   }
}
