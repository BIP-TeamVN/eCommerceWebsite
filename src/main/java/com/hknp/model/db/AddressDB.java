package com.hknp.model.db;

import com.hknp.interfaces.IModifySingleEntityAutoIncrement;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.AddressEntity;
import com.hknp.utils.EntityUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class AddressDB implements IRetrieveEntity<AddressEntity, Long>, IModifySingleEntityAutoIncrement<AddressEntity, Long> {

   private static AddressDB instance = null;

   private AddressDB() {
   }

   public static AddressDB getInstance() {
      if (instance == null) {
         instance = new AddressDB();
      }
      return instance;
   }

   @Override
   public ArrayList<AddressEntity> gets() {
      return null;
   }

   @Override
   public AddressEntity getById(Long id) {
      EntityManager entityMgr = EntityUtils.getEntityManager();
      return entityMgr.find(AddressEntity.class, id);
   }

   @Override
   public Long insert(AddressEntity entity) {
      return null;
   }

   @Override
   public boolean update(AddressEntity entity) {
      return false;
   }

   @Override
   public boolean delete(Long id) {
      return false;
   }
}
