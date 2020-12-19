package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.ProductEntity;
import com.hknp.model.entity.RateCommentEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class RateCommentDAO implements IRetrieveEntity<RateCommentEntity, Long>, IModifySingleEntityAutoIncrement<RateCommentEntity, Long> {
   private static RateCommentDAO instance = null;

   private RateCommentDAO() {
   }

   public static RateCommentDAO getInstance() {
      if (instance == null) {
         instance = new RateCommentDAO();
      }
      return instance;
   }

   @Override
   public Long insert(RateCommentEntity entity) {
      Long newId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newId = entity.getRateCommentId();

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
   public boolean update(RateCommentEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         RateCommentEntity rateCommentEntity = entityMgr.find(RateCommentEntity.class, id);
         entityMgr.remove(rateCommentEntity);

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
   public ArrayList<RateCommentEntity> gets() {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM RateCommentEntity u";
      TypedQuery<RateCommentEntity> typedQuery = entityMgr.createQuery(query, RateCommentEntity.class);

      ArrayList<RateCommentEntity> result = null;
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
   public RateCommentEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      RateCommentEntity rateCommentEntity = entityMgr.find(RateCommentEntity.class, id);
      return rateCommentEntity;
   }
}
