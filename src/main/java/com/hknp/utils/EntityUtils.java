package com.hknp.utils;

import com.hknp.model.entity.AddressEntity;

import javax.persistence.*;

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
}
