package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.Base64Utils;
import com.hknp.utils.EntityUtils;
import com.hknp.utils.HashUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class UserDAO implements IRetrieveEntity<UserEntity, Long>, IModifySingleEntityAutoIncrement<UserEntity, Long> {
   private static UserDAO instance = null;

   private UserDAO() {
   }

   public static UserDAO getInstance() {
      if (instance == null) {
         instance = new UserDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<UserEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<UserEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM UserEntity u";
      TypedQuery<UserEntity> typedQuery = entityMgr.createQuery(query, UserEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

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
      return entityMgr.find(UserEntity.class, id);
   }

   @Override
   public Long insert(UserEntity entity) {
      Long newUserId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

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
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
//      EntityManager entityMgr = EntityUtils.getEntityManager();
//      EntityTransaction entityTrans = null;
//
//      try {
//         entityTrans = entityMgr.getTransaction();
//         entityTrans.begin();
//
//         UserEntity userEntity = entityMgr.find(UserEntity.class, id);
//         entityMgr.remove(userEntity);
//
//         entityTrans.commit();
//      } catch (Exception e) {
//         if (entityTrans != null) {
//            entityTrans.rollback();
//         }
//         e.printStackTrace();
//         entityMgr.close();
//         return false;
//      }
      return false;
   }

   @Override
   public Long count() {
      return EntityUtils.count(UserEntity.class.getName());
   }

   /**
    * Check user login exist in User table on database
    *
    * @param username Id of user, pass 0 if null
    * @param password Email value to check
    * @return <code>id of user</code> if login successfully<br>
    * <code>0L</code> otherwise
    */
   public Long checkLogin(String username, String password) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      Long id = 0L;
      try {
         password = HashUtils.getMd5(Base64Utils.encodeFromString(password));

         String strQuery = "SELECT u.userId FROM UserEntity AS u " +
                 "WHERE (" +
                 "u.userName = :usernamePara " +
                 "or u.phoneNumber = :usernamePara " +
                 "or u.email = :usernamePara" +
                 ") " +
                 "and u.password = :passwordHash " +
                 "and u.status = true";

         Query query = entityMgr.createQuery(strQuery);
         query.setParameter("usernamePara", username);
         query.setParameter("passwordHash", password);
         query.setMaxResults(1);

         id = (Long) query.getSingleResult();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
      return id;
   }

   /**
    * Check email exist in User table on database
    *
    * @param userId Id of user, pass <code>0</code> if null
    * @param email  Email value to check
    * @return <code>true</code> if email not exits in the persistence context<br>
    * <code>false</code> otherwise
    * @return true if email not exist in database else false
    */
   public Boolean checkEmail(Long userId, String email) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      String queryStr = "select count(*) from UserEntity user where user.userId <> :userIdPara " +
              "and user.email = :emailPara";

      Query query = entityMgr.createQuery(queryStr);
      query.setParameter("userIdPara", userId);
      query.setParameter("emailPara", email);

      Long count = (Long) query.getSingleResult();
      return count == 0;
   }

   /**
    * Check phone number exist in User table on database
    *
    * @param userId      Id of user, pass <code>0</code> if null
    * @param phoneNumber Phone number value to check
    * @return <code>true</code> if phoneNumber not exits in the persistence context<br>
    * <code>false</code> otherwise
    * @return true if email not exist in database else false
    */
   public Boolean checkPhoneNumber(Long userId, String phoneNumber) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      String queryStr = "select count(*) from UserEntity user where user.userId <> :userIdPara " +
              "and user.phoneNumber = :phoneNumberPara";

      Query query = entityMgr.createQuery(queryStr);
      query.setParameter("userIdPara", userId);
      query.setParameter("phoneNumberPara", phoneNumber);

      Long count = (Long) query.getSingleResult();
      return count == 0;
   }

   public String getUserType(Long userId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      String queryStr = "select u.userType from UserEntity u where u.userId = :userIdPara";

      Query query = entityMgr.createQuery(queryStr)
              .setParameter("userIdPara", userId);

      Object result = null;
      try {
         result = query.getSingleResult();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         return result == null ? "" : (String) result;
      }
   }
}
