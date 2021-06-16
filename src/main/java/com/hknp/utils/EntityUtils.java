package com.hknp.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * This class provide methods that help you to work with <b>persistence context</b>
 */
public class EntityUtils {
   private static final EntityManagerFactory entityMgrFactory =
           Persistence.createEntityManagerFactory("eCommerceDb");

   /**
    * Get a entity manager
    *
    * @return {@link javax.persistence.EntityManager} object
    * @see EntityManagerFactory#createEntityManager()
    */
   public static EntityManager getEntityManager() {
      EntityManager entityMgr = entityMgrFactory.createEntityManager();
      return entityMgr;
   }

   /**
    * Merge the state of the given entity into the current persistence context.
    *
    * @param entity entity instance
    * @return <code>true</code> if updated successfully;<br>
    * <code>false</code> otherwise.
    * @see javax.persistence.EntityManager#merge(Object)
    */
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

   /**
    * Count number of row is contained in the persistence context
    *
    * @param table name of table in database
    * @return no of rows
    */
   public static Long count(String table) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String query = "SELECT COUNT(*) FROM " + table + " u";
      Long count = (Long) entityMgr.createQuery(query).getSingleResult();

      return count;
   }
}
