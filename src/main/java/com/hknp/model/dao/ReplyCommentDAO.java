package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.ReplyCommentEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ReplyCommentDAO implements IRetrieveEntity<ReplyCommentEntity, Long>, IModifySingleEntityAutoIncrement<ReplyCommentEntity, Long> {
   private static ReplyCommentDAO instance = null;

   private ReplyCommentDAO() {
   }

   public static ReplyCommentDAO getInstance() {
      if (instance == null) {
         instance = new ReplyCommentDAO();
      }
      return instance;
   }

   @Override
   public Long insert(ReplyCommentEntity entity) {
      Long newId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newId = entity.getSubCommentId();

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
   public boolean update(ReplyCommentEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         ReplyCommentEntity replyCommentEntity = entityMgr.find(ReplyCommentEntity.class, id);
         entityMgr.remove(replyCommentEntity);

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
   public ArrayList<ReplyCommentEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<ReplyCommentEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM ReplyCommentEntity u";
      TypedQuery<ReplyCommentEntity> typedQuery = entityMgr.createQuery(query, ReplyCommentEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<ReplyCommentEntity> result = null;
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
   public ReplyCommentEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(ReplyCommentEntity.class, id);
   }

   @Override
   public Long count() {
      return EntityUtils.count(ReplyCommentEntity.class.getName());
   }
}
