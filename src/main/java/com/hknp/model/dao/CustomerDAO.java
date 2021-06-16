package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.CustomerEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class CustomerDAO implements IRetrieveEntity<CustomerEntity, Long>, IModifySingleEntityAutoIncrement<CustomerEntity, Long> {
   private static CustomerDAO instance = null;

   private CustomerDAO() {
   }

   public static CustomerDAO getInstance() {
      if (instance == null) {
         instance = new CustomerDAO();
      }
      return instance;
   }

   @Override
   public Long insert(CustomerEntity entity) {
      Long newUserId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         newUserId = UserDAO.getInstance().insert(entity.getUserEntity());
         entity.setUserId(newUserId);
         entityMgr.persist(entity);

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
   public boolean update(CustomerEntity entity) {
      if (UserDAO.getInstance().update(entity.getUserEntity()))
         return EntityUtils.merge(entity);
      return false;
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         CustomerEntity customerEntity = entityMgr.find(CustomerEntity.class, id);
         entityMgr.remove(customerEntity);

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

   @Override
   public ArrayList<CustomerEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<CustomerEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM CustomerEntity u";
      TypedQuery<CustomerEntity> typedQuery = entityMgr.createQuery(query, CustomerEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<CustomerEntity> result = null;
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
   public CustomerEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(CustomerEntity.class, id);
   }

   @Override
   public Long count() {
      return EntityUtils.count(CustomerEntity.class.getName());
   }

   public Long count(String keyword) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr;
      Query query;

      queryStr = "select count(*) from CustomerEntity p " +
              "where concat(p.userEntity.firstName, ' ', p.userEntity.lastName) like :keywordPara " +
              "or p.userEntity.gender like :keywordPara " +
              "or p.userEntity.phoneNumber like :keywordPara " +
              "or p.userEntity.email like :keywordPara";
      query = entityMgr.createQuery(queryStr);

      query.setParameter("keywordPara", "%" + keyword + "%");

      return (Long) query.getSingleResult();
   }

   public ArrayList<CustomerEntity> gets(Integer firstResult, Integer maxResults, String keyword, String columnName, String typeSort) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      Query query;
      String queryStr;

      queryStr = "select u from CustomerEntity u " +
              "where concat(u.userEntity.firstName, ' ', u.userEntity.lastName) like :keywordPara " +
              "or u.userEntity.gender like :keywordPara " +
              "or u.userEntity.phoneNumber like :keywordPara " +
              "or u.userEntity.email like :keywordPara" + sortColumn(columnName, typeSort);

      query = entityMgr.createQuery(queryStr, CustomerEntity.class);

      query.setParameter("keywordPara", "%" + keyword + "%");

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<CustomerEntity> result = null;
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
