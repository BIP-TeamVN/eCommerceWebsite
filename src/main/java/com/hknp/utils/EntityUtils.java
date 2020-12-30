package com.hknp.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityUtils {
   private static final EntityManagerFactory entityMgrFactory = Persistence.createEntityManagerFactory("eCommerceDb");

   public static EntityManager getEntityManager() {
      EntityManager entityMgr = entityMgrFactory.createEntityManager();
      return entityMgr;
   }

   public static boolean merge(Object entity) {
      boolean isSucceed = true;
      EntityManager entityMgr = getEntityManager();
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
         isSucceed = false;
      } finally {
         entityMgr.close();
      }

      return isSucceed;
   }

   public static Long count(String table) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT COUNT(*) FROM " + table + " u";
      Long count = (Long) entityMgr.createQuery(query).getSingleResult();

      return count;
   }
}
