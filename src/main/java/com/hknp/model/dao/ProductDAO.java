package com.hknp.model.dao;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.ProductCategoryEntity;
import com.hknp.model.entity.ProductEntity;
import com.hknp.model.entity.ProductTypeEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IRetrieveEntity<ProductEntity, Long>, IModifySingleEntityAutoIncrement<ProductEntity, Long> {
   private static ProductDAO instance = null;

   private ProductDAO() {
   }

   public static ProductDAO getInstance() {
      if (instance == null) {
         instance = new ProductDAO();
      }
      return instance;
   }

   @Override
   public Long insert(ProductEntity entity) {
      Long newId = 0L;
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         Boolean typeResult = true;
         for (ProductTypeEntity type : entity.getProductTypeEntities()) {
            entityMgr.persist(type);
            Long newType = type.getProductTypeId();
            if (newType == 0) {
               typeResult = false;
               break;
            }
         }

         if (typeResult != false) {
            entityMgr.persist(entity);
            newId = entity.getProductId();

            entityTrans.commit();
         }
      } catch (Exception e) {
         if (entityTrans != null) {
            entityTrans.rollback();
         }
         e.printStackTrace();
      } finally {
         entityMgr.close();
      }

      return newId;
   }

   @Override
   public boolean update(ProductEntity entity) {
      return EntityUtils.merge(entity);
   }

   @Override
   public boolean delete(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction entityTrans = null;

      try {
         entityTrans = entityMgr.getTransaction();
         entityTrans.begin();

         ProductEntity productEntity = entityMgr.find(ProductEntity.class, id);
         entityMgr.remove(productEntity);

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
   public ArrayList<ProductEntity> gets() {
      return gets(null, null);
   }

   @Override
   public ArrayList<ProductEntity> gets(Integer firstResult, Integer maxResults) {
      return gets(firstResult, maxResults, 3, "", "", "");
   }

   public ArrayList<ProductEntity> gets(Integer firstResult, Integer maxResults, Integer status, String keyword, String columnName, String typeSort) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      Query query;
      String queryStr;
      if (status != 3) {
         queryStr = "SELECT u FROM ProductEntity u " +
                 "where u.status = :statusPara " +
                 "and (u.productName like :keywordPara " +
                 "or u.sellerEntity.storeName like :keywordPara " +
                 "or u.productOrigin like :keywordPara " +
                 "or u.brandEntity.brandName like :keywordPara)" + sortColumn(columnName, typeSort);

         query = entityMgr.createQuery(queryStr, ProductEntity.class);
         query.setParameter("statusPara", status);
      } else {
         queryStr = "select u from ProductEntity u " +
                 "where u.productName like :keywordPara " +
                 "or u.sellerEntity.storeName like :keywordPara " +
                 "or u.productOrigin like :keywordPara " +
                 "or u.brandEntity.brandName like :keywordPara" + sortColumn(columnName, typeSort);

         query = entityMgr.createQuery(queryStr, ProductEntity.class);
      }
      query.setParameter("keywordPara", "%" + keyword + "%");

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<ProductEntity> result = null;
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

   public ArrayList<ProductEntity> gets(Integer firstResult, Integer maxResults, Long sellerId, Integer status, String keyword, String columnName, String typeSort) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      Query query;
      String queryStr;
      if (status != 3) {
         queryStr = "SELECT u FROM ProductEntity u " +
                 "where u.sellerEntity.userId = :sellerIdPara " +
                 "and u.status = :statusPara " +
                 "and (u.productName like :keywordPara " +
                 "or u.sellerEntity.storeName like :keywordPara " +
                 "or u.productOrigin like :keywordPara " +
                 "or u.brandEntity.brandName like :keywordPara)" + sortColumn(columnName, typeSort);

         query = entityMgr.createQuery(queryStr, ProductEntity.class);
         query.setParameter("statusPara", status);
      } else {
         queryStr = "SELECT u FROM ProductEntity u " +
                 "where u.sellerEntity.userId = :sellerIdPara " +
                 "and (u.productName like :keywordPara " +
                 "or u.sellerEntity.storeName like :keywordPara " +
                 "or u.productOrigin like :keywordPara " +
                 "or u.brandEntity.brandName like :keywordPara)" + sortColumn(columnName, typeSort);

         query = entityMgr.createQuery(queryStr, ProductEntity.class);
      }
      query.setParameter("sellerIdPara", sellerId);
      query.setParameter("keywordPara", "%" + keyword + "%");

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<ProductEntity> result = null;
      try {
         result = new ArrayList<>(query.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   @Override
   public ProductEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(ProductEntity.class, id);
   }

   public ProductEntity getByIdForCustomer(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      ProductEntity productEntity = entityMgr.find(ProductEntity.class, id);
      if (productEntity.getStatus() == 1) {
         return productEntity;
      } else {
         return null;
      }
   }

   public ProductEntity getByIdAndSeller(Long productId, Long sellerId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      try {
         String query = "SELECT u FROM ProductEntity AS u WHERE u.sellerEntity.userId = " + sellerId + " and u.productId = " + productId;
         return entityMgr.createQuery(query, ProductEntity.class).getSingleResult();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
      return null;
   }

   public ProductEntity getByIdAndSeller(Long productId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      try {
         String query = "SELECT u FROM ProductEntity AS u WHERE u.productId = " + productId;
         return entityMgr.createQuery(query, ProductEntity.class).getSingleResult();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
      return null;
   }

   @Override
   public Long count() {
      return EntityUtils.count(ProductEntity.class.getName());
   }

   public Long count(Integer status, String keyword) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr;
      Query query;
      if (status != 3) {
         queryStr = "select count(*) from ProductEntity p " +
                 "where p.status = :statusPara " +
                 "and (p.productName like :keywordPara " +
                 "or p.sellerEntity.storeName like :keywordPara " +
                 "or p.productOrigin like :keywordPara " +
                 "or p.brandEntity.brandName like :keywordPara)";
         query = entityMgr.createQuery(queryStr);
         query.setParameter("statusPara", status);
      } else {
         queryStr = "select count(*) from ProductEntity p " +
                 "where p.productName like :keywordPara " +
                 "or p.sellerEntity.storeName like :keywordPara " +
                 "or p.productOrigin like :keywordPara " +
                 "or p.brandEntity.brandName like :keywordPara";
         query = entityMgr.createQuery(queryStr);
      }
      query.setParameter("keywordPara", "%" + keyword + "%");

      return (Long) query.getSingleResult();
   }

   public Long count(Long sellerId, Integer status, String keyword) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr;
      Query query;
      if (status != 3) {
         queryStr = "select count(*) from ProductEntity p " +
                 "where p.sellerEntity.userId = :sellerIdPara " +
                 "and p.status = :statusPara " +
                 "and (p.productName like :keywordPara " +
                 "or p.sellerEntity.storeName like :keywordPara " +
                 "or p.productOrigin like :keywordPara " +
                 "or p.brandEntity.brandName like :keywordPara)";
         query = entityMgr.createQuery(queryStr);
         query.setParameter("statusPara", status);
      } else {
         queryStr = "select count(*) from ProductEntity p " +
                 "where p.sellerEntity.userId = :sellerIdPara " +
                 "and (p.productName like :keywordPara " +
                 "or p.sellerEntity.storeName like :keywordPara " +
                 "or p.productOrigin like :keywordPara " +
                 "or p.brandEntity.brandName like :keywordPara)";
         query = entityMgr.createQuery(queryStr);
      }
      query.setParameter("sellerIdPara", sellerId);
      query.setParameter("keywordPara", "%" + keyword + "%");

      return (Long) query.getSingleResult();
   }

   public Boolean updateStatus(Long productId, Integer status) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      EntityTransaction tran = entityMgr.getTransaction();
      tran.begin();

      String queryStr = "update ProductEntity as p set p.status = :statusPara where p.productId = :productIdPara";
      Query query = entityMgr.createQuery(queryStr);
      query.setParameter("productIdPara", productId);
      query.setParameter("statusPara", status);

      Integer result = query.executeUpdate();

      tran.commit();
      return result > 0;
   }

   public Integer getStatusById(Long productId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr = "select p.status from ProductEntity as p where p.productId = :productIdPara";
      Query query = entityMgr.createQuery(queryStr);
      query.setParameter("productIdPara", productId);

      return (Integer) query.getSingleResult();
   }

   public Long getCountProductSold(Long productId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr = "SELECT SUM(bd.quantity) " +
              "FROM BillDetailEntity bd INNER JOIN ProductTypeEntity pt " +
              "ON bd.productTypeEntity.productTypeId = pt.productTypeId " +
              "WHERE pt.productEntity.productId = :productIdPara";
      Query query = entityMgr.createQuery(queryStr);
      query.setParameter("productIdPara", productId);

      Long result = (Long) query.getSingleResult();
      return result == null ? 0 : result;
   }

   public Long countProductSell(Long sellerId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr;
      Query query;
      queryStr = "select count(*) from ProductEntity p " +
              "where p.sellerEntity.userId = :sellerIdPara " +
              "and p.status = 1 ";
      query = entityMgr.createQuery(queryStr);
      query.setParameter("sellerIdPara", sellerId);
      return (Long) query.getSingleResult();
   }

   public Long getCountProductInStock(Long productId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr = "SELECT SUM(pt.quantity) " +
              "FROM ProductEntity bd INNER JOIN ProductTypeEntity pt " +
              "ON bd.productId = pt.productEntity.productId " +
              "WHERE bd.productId = :productIdPara";
      Query query = entityMgr.createQuery(queryStr);
      query.setParameter("productIdPara", productId);

      Long result = (Long) query.getSingleResult();
      return result == null ? 0 : result;
   }

   public ArrayList<ProductEntity> getProductBySellerId(Integer firstResult, Integer maxResults, Long sellerId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      Query query;
      String queryStr;

      queryStr = "SELECT u FROM ProductEntity u " +
              "where u.sellerEntity.userId = :sellerIdPara " +
              "and u.status = 1 order by u.productCreateDate asc";

      query = entityMgr.createQuery(queryStr, ProductEntity.class);

      query.setParameter("sellerIdPara", sellerId);

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<ProductEntity> result = null;
      try {
         result = new ArrayList<>(query.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public ArrayList<ProductEntity> getProductByCategory(Integer firstResult, Integer maxResults, Long productId) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      ProductEntity productEntity = getById(productId);

      ArrayList<ProductCategoryEntity> categoryEntities = new ArrayList<>(productEntity.getProductCategoryEntities());
      List<Long> categoryIds = new ArrayList<>();
      for (ProductCategoryEntity p : categoryEntities) {
         categoryIds.add(p.getProductCategoryId());
      }

      String queryStr = "SELECT distinct u FROM ProductEntity u join u.productCategoryEntities r where r.productCategoryId in :Ids";
      Query query = entityMgr.createQuery(queryStr, ProductEntity.class);
      query.setParameter("Ids", categoryIds);

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<ProductEntity> result = null;
      try {
         result = new ArrayList<>(query.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }

   public ArrayList<ProductEntity> gets(Integer firstResult, Integer maxResults, String keyword, String columnName, String typeSort, List<Long> categories, List<Long> brands) {
      EntityManager entityMgr = EntityUtils.getEntityManager();

      String queryStr;
      Query query;
      if (brands.size() == 0 && categories.size() == 0) {
         queryStr = "SELECT distinct u FROM ProductEntity u join u.productCategoryEntities r " +
                 "where u.status = 1 " +
                 "and (u.productName like :keywordPara " +
                 "or u.sellerEntity.storeName like :keywordPara " +
                 "or u.productOrigin like :keywordPara " +
                 "or u.brandEntity.brandName like :keywordPara)" + sortColumn(columnName, typeSort);
         query = entityMgr.createQuery(queryStr, ProductEntity.class);
      } else if (brands.size() == 0) {
         queryStr = "SELECT distinct u FROM ProductEntity u join u.productCategoryEntities r " +
                 "where u.status = 1 " +
                 "and r.productCategoryId in :categoriesPara " +
                 "and (u.productName like :keywordPara " +
                 "or u.sellerEntity.storeName like :keywordPara " +
                 "or u.productOrigin like :keywordPara " +
                 "or u.brandEntity.brandName like :keywordPara)" + sortColumn(columnName, typeSort);
         query = entityMgr.createQuery(queryStr, ProductEntity.class);
         query.setParameter("categoriesPara", categories);
      } else if (categories.size() == 0) {
         queryStr = "SELECT distinct u FROM ProductEntity u join u.productCategoryEntities r " +
                 "where u.status = 1 " +
                 "and u.brandEntity.brandId in :brandsPara " +
                 "and (u.productName like :keywordPara " +
                 "or u.sellerEntity.storeName like :keywordPara " +
                 "or u.productOrigin like :keywordPara " +
                 "or u.brandEntity.brandName like :keywordPara)" + sortColumn(columnName, typeSort);
         query = entityMgr.createQuery(queryStr, ProductEntity.class);
         query.setParameter("brandsPara", brands);
      } else {
         queryStr = "SELECT distinct u FROM ProductEntity u join u.productCategoryEntities r " +
                 "where u.status = 1 " +
                 "and (r.productCategoryId in :categoriesPara or u.brandEntity.brandId in :brandsPara) " +
                 "and (u.productName like :keywordPara " +
                 "or u.sellerEntity.storeName like :keywordPara " +
                 "or u.productOrigin like :keywordPara " +
                 "or u.brandEntity.brandName like :keywordPara)" + sortColumn(columnName, typeSort);
         query = entityMgr.createQuery(queryStr, ProductEntity.class);
         query.setParameter("categoriesPara", categories);
         query.setParameter("brandsPara", brands);
      }

      query.setParameter("keywordPara", "%" + keyword + "%");

      if (firstResult != null) {
         query.setFirstResult(firstResult);
      }
      if (maxResults != null) {
         query.setMaxResults(maxResults);
      }

      ArrayList<ProductEntity> result = null;
      try {
         result = new ArrayList<>(query.getResultList());
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         entityMgr.close();
      }
      return result;
   }
}
