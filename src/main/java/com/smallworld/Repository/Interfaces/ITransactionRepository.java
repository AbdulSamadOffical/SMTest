package com.smallworld.Repository.Interfaces;

import com.smallworld.Entity.TransactionEntity;

import java.util.List;

public interface ITransactionRepository {
    List<TransactionEntity> getTotalTransactionAmount();
}
