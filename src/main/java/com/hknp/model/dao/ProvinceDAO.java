package com.hknp.model.dao;

import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.ProvinceEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ProvinceDAO implements IRetrieveEntity<ProvinceEntity, String> {
   private static ProvinceDAO instance = null;

   private ProvinceDAO() {
   }

   public static ProvinceDAO getInstance() {
      if (instance == null) {
         instance = new ProvinceDAO();
      }
      return instance;
   }

   @Override
   public ArrayList<ProvinceEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<ProvinceEntity> gets(Integer firstResult, Integer maxResults) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT p FROM ProvinceEntity p";
      TypedQuery<ProvinceEntity> typedQuery = entityMgr.createQuery(query, ProvinceEntity.class);

      if (firstResult != null) {
         typedQuery.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         typedQuery.setMaxResults(maxResults);
      }

      ArrayList<ProvinceEntity> result = null;
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
   public ProvinceEntity getById(String id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String hql = "SELECT p FROM ProvinceEntity p WHERE p.provinceId = :id";
      TypedQuery<ProvinceEntity> typedQuery = entityMgr.createQuery(hql, ProvinceEntity.class);
      typedQuery.setParameter("id", id);

      ProvinceEntity province = null;
      try {
         province = typedQuery.getSingleResult();
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return province;
   }

   @Override
   public Long count() {
      return EntityUtils.count(ProvinceEntity.class.getName());
   }
}
