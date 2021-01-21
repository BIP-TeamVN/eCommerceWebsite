package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.AddressEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class AddressDAO implements IRetrieveEntity<AddressEntity, Long>, IModifySingleEntityAutoIncrement<AddressEntity, Long> {
   private static AddressDAO instance = null;

   private AddressDAO() {
   }

   public static AddressDAO getInstance() {
      if (instance == null) {
         instance = new AddressDAO();
      }
      return instance;
   }

   @Override
   public Long insert(AddressEntity entity) {
      Long newAddressId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         entityMgr.persist(entity);
         newAddressId = entity.getAddressId();

         entityTrans.commit();
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
      } finally {
         entityMgr.close();
      }

      return newAddressId;
   }

   @Override
   public boolean update(AddressEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         AddressEntity addressEntity = entityMgr.find(AddressEntity.class, id);
         entityMgr.remove(addressEntity);

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
   public ArrayList<AddressEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<AddressEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT u FROM AddressEntity u";
      TypedQuery<AddressEntity> typedQuery = entityMgr.createQuery(query, AddressEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<AddressEntity> result = null;
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
   public AddressEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(AddressEntity.class, id);
   }

   @Override
   public Long count() {
      return EntityUtils.count(AddressEntity.class.getName());
   }
}
