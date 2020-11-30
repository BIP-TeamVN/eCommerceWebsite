package com.hknp.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Provide method to word with table in database
 * @param <DtoType> Data type of DTO object
 * @param <IdType> Data type of identity column
 */
public interface IDatabaseAccess<IdType, DtoType> {
    /**
     * Select from database and convert to ArrayList&#60;DTOObject&#62;
     * @return either (1) ArrayList&#60;DTOObject&#62; or (2) null for getting failed
     */
    public ArrayList<DtoType> gets();
    /**
     * Insert new record to table
     * @param id identity of Object
     * @return either (1) DTOObject get from first record in table or (2) null for not record match
     */
    public DtoType getById(IdType id);
    /**
     * Insert new record to table
     * @param dto ObjectDto to insert
     * @return null if insert failed, 1 or inserted id of new record
     */
    public Object insert(DtoType dto);
    /**
     * Update table
     * @param dto ObjectDto to update
     * @return number of the row changed in table
     */
    public int update(DtoType dto);
    /**
     * Delete record in table
     * @param id identity of record
     * @return number of the row changed in table
     */
    public int delete(IdType id);
}
