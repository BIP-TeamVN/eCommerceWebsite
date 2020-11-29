package com.hknp.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDatabaseAccess<IdType, DtoType> {
    public ArrayList<DtoType> gets();
    public DtoType getById(IdType id);
    public int insert(DtoType dto);
    public int update(DtoType dto);
    public int delete(IdType id);
}
