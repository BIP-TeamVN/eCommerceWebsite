package com.hknp.model.db;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class UserDB implements IRetrieveEntity<UserEntity, Long>, IModifySingleEntityAutoIncrement<UserEntity, Long> {
   private static UserDB instance = null;

   private UserDB() {
   }

   public static UserDB getInstance() {
      if (instance == null) {
         instance = new UserDB();
      }
      return instance;
   }

   @Override
   public ArrayList<UserEntity> gets() {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM UserEntity u";
      TypedQuery<UserEntity> typedQuery = entityMgr.createQuery(query, UserEntity.class);

      ArrayList<UserEntity> result = null;
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
   public UserEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      UserEntity user = entityMgr.find(UserEntity.class, id);
      return user;
   }

   @Override
   public Long insert(UserEntity entity) {
      Long newUserId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         //entityMgr.detach(entity);
         entityMgr.persist(entity);
         newUserId = entity.getUserId();

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
      } finally {
         entityMgr.close();
      }

      return newUserId;
   }

   @Override
   public boolean update(UserEntity entity) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.merge(entity);

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
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         UserEntity userEntity = entityMgr.find(UserEntity.class, id);
         entityMgr.remove(userEntity);

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
}
