package com.hknp.interfaces;

import java.util.ArrayList;

public interface IRetrieveEntity<EntityType, IdType> {
   ArrayList<EntityType> gets();

   EntityType getById(IdType id);

   Long count();
}