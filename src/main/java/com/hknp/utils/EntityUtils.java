package com.hknp.utils;

import com.hknp.model.entity.EmployeeEntity;

import javax.persistence.*;
import java.util.ArrayList;
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

   public static Long count(String table) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT COUNT(*) FROM " + table + " u";
      Long count = (Long) entityMgr.createQuery(query).getSingleResult();

      return count;
   }
}
