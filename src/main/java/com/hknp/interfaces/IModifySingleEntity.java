package com.hknp.interfaces;

public interface IModifySingleEntity<EntityType, IdType> {
   boolean insert(EntityType entity);

   boolean update(EntityType entity);

   boolean delete(IdType id);
}
