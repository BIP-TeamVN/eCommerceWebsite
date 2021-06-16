package com.hknp.interfaces;

import java.util.ArrayList;

/**
 * The  {@link com.hknp.interfaces.IRetrieveEntity} interface
 * is a interface for <b><i>Retrieve Entity</i></b> in the persistence context
 *
 * @param <EntityType> Data type of EntityType object
 * @param <IdType>     Data type of identity column
 * @see com.hknp.interfaces.IModifySingleEntity
 * @see com.hknp.interfaces.IModifySingleEntityAutoIncrement
 */
public interface IRetrieveEntity<EntityType, IdType> {
   /**
    * Create and execute a HQL SELECT query from database and
    * convert to ArrayList&#60;{@link EntityType}&#62;
    *
    * @return ArrayList&#60;{@link EntityType}&#62; for retrieve entities successfully<br>
    * <code>null</code> for retrieve entities failed
    */
   ArrayList<EntityType> gets();

   /**
    * Execute HQL select from database and convert to ArrayList&#60;{@link EntityType}&#62;
    *
    * @param firstResult position of the first result, numbered from 0, if <b>null</b> not set
    * @param maxResults  maximum number of results to retrieve, if <b>null</b> not set
    * @return ArrayList&#60;{@link EntityType}&#62; for retrieve entities successfully<br>
    * <code>null</code> for retrieve entities failed
    */
   ArrayList<EntityType> gets(Integer firstResult, Integer maxResults);

   /**
    * Find by primary key. Search for an entity of the specified class and primary key.
    * If the entity instance is contained in the persistence context, it is returned from there.
    *
    * @param id primary key value
    * @return the found entity instance or null if the entity does not exist
    */
   EntityType getById(IdType id);

   /**
    * Count number of row is contained in the persistence context
    *
    * @return no of rows
    */
   Long count();
}