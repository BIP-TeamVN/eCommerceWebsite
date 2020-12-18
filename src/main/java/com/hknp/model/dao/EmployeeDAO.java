package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
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
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM EmployeeEntity u";
      TypedQuery<EmployeeEntity> typedQuery = entityMgr.createQuery(query, EmployeeEntity.class);

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
      EmployeeEntity employeeEntity = entityMgr.find(EmployeeEntity.class, id);
      return employeeEntity;
   }
}
