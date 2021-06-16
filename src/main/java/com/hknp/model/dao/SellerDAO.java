package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.SellerEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class SellerDAO implements IRetrieveEntity<SellerEntity, Long>, IModifySingleEntityAutoIncrement<SellerEntity, Long> {
   private static SellerDAO instance = null;

   private SellerDAO() {
   }

   public static SellerDAO getInstance() {
      if (instance == null) {
         instance = new SellerDAO();
      }
      return instance;
   }

   @Override
   public Long insert(SellerEntity entity) {
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
   public boolean update(SellerEntity entity) {
      if (UserDAO.getInstance().update(entity.getUserEntity())) {
         return EntityUtils.merge(entity);
      }
      return false;
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         SellerEntity sellerEntity = entityMgr.find(SellerEntity.class, id);
         entityMgr.remove(sellerEntity);

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
   public ArrayList<SellerEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<SellerEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM SellerEntity u";
      TypedQuery<SellerEntity> typedQuery = entityMgr.createQuery(query, SellerEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<SellerEntity> result = null;
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
   public SellerEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(SellerEntity.class, id);
   }

   public ArrayList<SellerEntity> gets(Integer firstResult, Integer maxResults, String searchKeyword, Integer status, String sortColumnName, String typeSort) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      Query query;
      String queryStr;
      Boolean temp;
      if (status == 2) {
         queryStr = "SELECT u FROM SellerEntity  u " +
                 "where concat(u.userEntity.firstName , ' ', u.userEntity.lastName) like :searchKeyword " +
                 "or u.userEntity.gender like :searchKeyword " +
                 "or  u.storeName like :searchKeyword " +
                 "or u.storeLink like :searchKeyword " +
                 "or u.businessLicenseId like :searchKeyword " +
                 "or u.bankAccountId like :searchKeyword " + sortColumn(sortColumnName, typeSort);
         query = entityMgr.createQuery(queryStr, SellerEntity.class);
      } else {
         temp = status == 1;
         queryStr = "SELECT u FROM SellerEntity  u " +
                 "where u.userEntity.status = :statusPara " +
                 "and (concat(u.userEntity.firstName , ' ', u.userEntity.lastName) like :searchKeyword " +
                 "or u.userEntity.gender like :searchKeyword " +
                 "or  u.storeName like :searchKeyword " +
                 "or u.storeLink like :searchKeyword " +
                 "or  u.businessLicenseId like :searchKeyword " +
                 "or u.bankAccountId like :searchKeyword) " + sortColumn(sortColumnName, typeSort);
         query = entityMgr.createQuery(queryStr, SellerEntity.class);
         query.setParameter("statusPara", temp);
      }
      query.setParameter("searchKeyword", "%" + searchKeyword + "%");

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<SellerEntity> result = null;
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
         queryStr = String.format("SELECT count(*) FROM SellerEntity  u " +
                 "where concat(u.userEntity.firstName , ' ', u.userEntity.lastName) like :searchKeyword " +
                 "or u.userEntity.phoneNumber like :searchKeyword " +
                 "or  u.userEntity.email like :searchKeyword " +
                 "or u.userEntity.gender like :searchKeyword ");
         query = entityMgr.createQuery(queryStr);
         query.setParameter("searchKeyword", "%" + keyword + "%");
      } else {
         temp = status == 1;
         queryStr = String.format("SELECT COUNT(*) FROM SellerEntity  u " +
                 "where u.userEntity.status = :statusPara " +
                 "and (concat(u.userEntity.firstName , ' ', u.userEntity.lastName) like :searchKeyword " +
                 "or u.userEntity.gender like :searchKeyword " +
                 "or  u.storeName like :searchKeyword " +
                 "or u.storeLink like :searchKeyword " +
                 "or  u.businessLicenseId like :searchKeyword " +
                 "or u.bankAccountId like :searchKeyword) ");
         query = entityMgr.createQuery(queryStr);
         query.setParameter("statusPara", temp);
         query.setParameter("searchKeyword", "%" + keyword + "%");
      }

      return (Long) query.getSingleResult();
   }

   @Override
   public Long count() {
      return EntityUtils.count(SellerEntity.class.getName());
   }
}
