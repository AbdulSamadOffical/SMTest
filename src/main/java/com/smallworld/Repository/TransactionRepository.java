package com.smallworld.Repository;

import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Repository.Interfaces.IBaseRepository;
import com.smallworld.Repository.Interfaces.ITransactionRepository;

import java.util.List;

public class TransactionRepository  implements ITransactionRepository {
    IBaseRepository<TransactionEntity> baseRepository;
    List<TransactionEntity> datasource;
    public TransactionRepository(IBaseRepository<TransactionEntity> _baserepository, List<TransactionEntity> _datasource){

        this.baseRepository = _baserepository;
        this.datasource = _datasource;
    }


    @Override
    public List<TransactionEntity> getTotalTransactionAmount() {
        return datasource.stream()
                .filter(TransactionEntity.distinctBy(TransactionEntity::getMtn))
                .toList();

    }
}
