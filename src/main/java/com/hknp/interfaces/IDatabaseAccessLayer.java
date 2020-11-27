package com.hknp.interfaces;

import java.util.List;

public interface IDatabaseAccessLayer<IdType, ModelType> {
    public List<ModelType> Gets();
    public ModelType GetById(IdType id);
    public boolean Add(ModelType newModel);
    public boolean Update(ModelType newModel);
    public boolean Delete(IdType id);
}
