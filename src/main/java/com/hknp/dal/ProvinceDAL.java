package com.hknp.dal;

import com.hknp.interfaces.IDatabaseAccessLayer;
import com.hknp.model.ProvinceModel;

import java.util.List;

public class ProvinceDAL implements IDatabaseAccessLayer<String ,ProvinceModel> {
    @Override
    public List<ProvinceModel> Gets() {
        return null;
    }

    @Override
    public ProvinceModel GetById(String id) {
        return null;
    }

    @Override
    public boolean Add(ProvinceModel newModel) {
        return false;
    }

    @Override
    public boolean Update(ProvinceModel newModel) {
        return false;
    }

    @Override
    public boolean Delete(String id) {
        return false;
    }
}
