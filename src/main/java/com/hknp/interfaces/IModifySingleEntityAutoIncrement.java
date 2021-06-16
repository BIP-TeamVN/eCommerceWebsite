package com.hknp.interfaces;

/**
 * The  {@link com.hknp.interfaces.IModifySingleEntityAutoIncrement} interface
 * is a interface for <b><i>Modify Single Entity</i></b> which has <b>Auto Increment column</b>
 *
 * @param <EntityType> Data type of EntityType object
 * @param <IdType>     Data type of identity column
 * @see com.hknp.interfaces.IModifySingleEntity
 * @see com.hknp.interfaces.IRetrieveEntity
 */
public interface IModifySingleEntityAutoIncrement<EntityType, IdType> {
   /**
    * Insert new entity into the persistence context (database)
    *
    * @param entity new entity to insert (must set identity column to null or default)
    * @return <code>id of new entity</code> for inserted new entity successfully<br>
    * <code>false</code> otherwise
    * @see com.hknp.utils.EntityUtils#merge(Object)
    * @see javax.persistence.EntityManager#merge(Object)
    */
   IdType insert(EntityType entity);

   /**
    * Update entity in the persistence context (database)
    *
    * @param entity update entity
    * @return <code>true</code> for updated entity successfully<br>
    * <code>false</code> otherwise
    * @see com.hknp.utils.EntityUtils#merge(Object)
    * @see javax.persistence.EntityManager#merge(Object)
    */
   boolean update(EntityType entity);

   /**
    * Delete entity in the persistence context (database)
    *
    * @param id id of entity want delete
    * @return <code>true</code> for deleted entity successfully<br>
    * <code>false</code> otherwise
    * @see javax.persistence.EntityManager#remove(Object)
    */
   boolean delete(IdType id);
}