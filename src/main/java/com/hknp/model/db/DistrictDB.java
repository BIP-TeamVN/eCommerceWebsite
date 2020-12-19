package com.hknp.model.db;

import com.hknp.interfaces.IModifySingleEntity;
import com.hknp.interfaces.IRetrieveEntity;
import com.hknp.model.entity.CommuneEntity;
import com.hknp.model.entity.DistrictEntity;

import java.util.ArrayList;

public class DistrictDB implements IRetrieveEntity<DistrictEntity, String>, IModifySingleEntity<DistrictEntity, String> {

   @Override
   public boolean insert(DistrictEntity entity) {
      return false;
   }

   @Override
   public boolean update(DistrictEntity entity) {
      return false;
   }

   @Override
   public boolean delete(String id) {
      return false;
   }

   @Override
   public ArrayList<DistrictEntity> gets() {
      return null;
   }

   @Override
   public DistrictEntity getById(String id) {
      return null;
   }
}
