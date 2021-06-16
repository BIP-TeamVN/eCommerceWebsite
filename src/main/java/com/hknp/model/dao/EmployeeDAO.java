package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;

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
   public ArrayList<EmployeeEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<EmployeeEntity> gets(Integer firstResult, Integer maxResults) {
      return gets(firstResult, maxResults, null, null, null, null);
   }

   public ArrayList<EmployeeEntity> gets(Integer firstResult, Integer maxResults, String searchKeyword, Integer status, String sortColumnName, String typeSort) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      Query query;
      String queryStr;
      Boolean temp;
      if (status == 2) {
         queryStr = "SELECT u FROM EmployeeEntity  u " +
                 "where concat(u.userEntity.firstName , ' ', u.userEntity.lastName) like :searchKeyword " +
                 "or u.userEntity.phoneNumber like :searchKeyword " +
                 "or  u.userEntity.email like :searchKeyword " +
                 "or u.userEntity.gender like :searchKeyword " +
                 "or u.salary like :searchKeyword " + sortColumn(sortColumnName, typeSort);
         query = entityMgr.createQuery(queryStr, EmployeeEntity.class);
      } else {
         temp = status == 1;
         queryStr = "SELECT u FROM EmployeeEntity  u " +
                 "where u.userEntity.status = :statusPara " +
                 "and (concat(u.userEntity.firstName , ' ', u.userEntity.lastName) like :searchKeyword " +
                 "or u.userEntity.phoneNumber like :searchKeyword " +
                 "or  u.userEntity.email like :searchKeyword " +
                 "or u.userEntity.gender like :searchKeyword " +
                 "or u.salary like :searchKeyword) " + sortColumn(sortColumnName, typeSort);
         query = entityMgr.createQuery(queryStr, EmployeeEntity.class);
         query.setParameter("statusPara", temp);
      }
      query.setParameter("searchKeyword", "%" + searchKeyword + "%");

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<EmployeeEntity> result = null;
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

   public Long count(Integer status, String keyword) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr;
      Query query;
      Boolean temp;
      if (status == 2) {
         queryStr = String.format("SELECT count(*) FROM EmployeeEntity  u " +
                 "where concat(u.userEntity.firstName , ' ', u.userEntity.lastName) like :searchKeyword " +
                 "or u.userEntity.phoneNumber like :searchKeyword " +
                 "or  u.userEntity.email like :searchKeyword " +
                 "or u.userEntity.gender like :searchKeyword " +
                 "or u.salary like :searchKeyword ");
         query = entityMgr.createQuery(queryStr);
         query.setParameter("searchKeyword", "%" + keyword + "%");
      } else {
         temp = status == 1;
         queryStr = String.format("SELECT count(*) FROM EmployeeEntity  u " +
                 "where u.userEntity.status = :statusPara " +
                 "and (concat(u.userEntity.firstName , ' ', u.userEntity.lastName) like :searchKeyword " +
                 "or u.userEntity.phoneNumber like :searchKeyword " +
                 "or  u.userEntity.email like :searchKeyword " +
                 "or u.userEntity.gender like :searchKeyword " +
                 "or u.salary like :searchKeyword) ");
         query = entityMgr.createQuery(queryStr);
         query.setParameter("statusPara", temp);
         query.setParameter("searchKeyword", "%" + keyword + "%");
      }

      return (Long) query.getSingleResult();
   }

   @Override
   public EmployeeEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(EmployeeEntity.class, id);
   }

   @Override
   public Long count() {
      return EntityUtils.count(EmployeeEntity.class.getName());
   }
}
