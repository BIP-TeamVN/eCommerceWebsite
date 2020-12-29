package com.hknp.interfaces;

import java.util.ArrayList;

public interface IRetrieveEntity<EntityType, IdType> {
   ArrayList<EntityType> gets();

   ArrayList<EntityType> gets(Integer firstResult, Integer maxResults);

   EntityType getById(IdType id);
}