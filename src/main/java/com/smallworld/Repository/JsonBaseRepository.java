package com.smallworld.Repository;


import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Repository.Interfaces.IBaseRepository;

import java.util.List;

public class JsonBaseRepository<T> implements IBaseRepository<T> {
    List<T> datasource;
    public JsonBaseRepository(List<T> _datasource){
        this.datasource = _datasource;
    }

    @Override
    public List<T> getAll() {
        return this.datasource;
    }

}
