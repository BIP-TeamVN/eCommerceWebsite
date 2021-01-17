package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Queue;

public class EmployeeDAO implements IRetrieveEntity<EmployeeEntity, Long>, IModifySingleEntityAutoIncrement<EmployeeEntity, Long> {
   private static EmployeeDAO instance = null;

   private EmployeeDAO() {
   }

   public static EmployeeDAO getInstance() {
      if (instance == null) {
         instance = new EmployeeDAO();
      }
      return instance;
   }

   @Override
   public Long insert(EmployeeEntity entity) {
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
   public boolean update(EmployeeEntity entity) {
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

         EmployeeEntity employeeEntity = entityMgr.find(EmployeeEntity.class, id);
         entityMgr.remove(employeeEntity);

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
   public ArrayList<EmployeeEntity> gets() { return gets(null, null); }

   @Override
   public ArrayList<EmployeeEntity> gets(Integer firstResult, Integer maxResults) {
      return gets(firstResult, maxResults, null, null,null);
   }

   public ArrayList<EmployeeEntity> gets(Integer firstResult, Integer maxResults, String searchKeyword, Integer status, String sortColumnName) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      String query = "SELECT u FROM EmployeeEntity u";

      if (searchKeyword != null && status != null) {
         query += " where CONCAT(u.userEntity.lastName, ' ', u.userEntity.firstName) like :keywordPara and u.userEntity.status = :statusPara";
      } else if (searchKeyword == null && status != null) {
         query += " where u.userEntity.status :statusPara";
      } else if (searchKeyword != null && status == null) {
         query += " where CONCAT(u.userEntity.lastName, ' ', u.userEntity.firstName) like :keywordPara";
      }

      if (sortColumnName != null && !sortColumnName.isEmpty()) {
         query += " order by u." + sortColumnName;
      }

      TypedQuery typedQuery = entityMgr.createQuery(query, EmployeeEntity.class);

      if (searchKeyword != null) {
         typedQuery.setParameter("keywordPara", "%" + searchKeyword + "%");
      }
      if (status != null) {
         typedQuery.setParameter("statusPara", status);
      }

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<EmployeeEntity> result = null;
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
   public EmployeeEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(EmployeeEntity.class, id);
   }

   @Override
   public Long count() {return EntityUtils.count(EmployeeEntity.class.getName());}
}
