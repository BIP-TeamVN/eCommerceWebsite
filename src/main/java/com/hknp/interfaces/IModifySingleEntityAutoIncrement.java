package com.hknp.interfaces;

public interface IModifySingleEntityAutoIncrement<EntityType, IdType> {
   IdType insert(EntityType entity);

   boolean update(EntityType entity);

   boolean delete(IdType id);
}
