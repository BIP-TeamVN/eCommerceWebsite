package com.hknp.interfaces;

public interface IDataUpdateAutoIncrement<IdType, DtoType> {
   /**
    * Insert new record to table
    *
    * @param dto ObjectDto to insert
    * @return null or 0 if insert failed, inserted id of new record
    */
   IdType insert(DtoType dto);

   /**
    * Update table
    *
    * @param dto ObjectDto to update
    * @return number of the row changed in table
    */
   int update(DtoType dto);

   /**
    * Delete record in table
    *
    * @param id identity of record
    * @return number of the row changed in table
    */
   int delete(IdType id);
}
