package com.smallworld;

import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Mapper.JsonToEntityMapper;
import com.smallworld.Repository.JsonBaseRepository;
import com.smallworld.Repository.Transactions.MockTransactionRepository;
import com.smallworld.Repository.Transactions.TransactionRepository;
import com.smallworld.services.TransactionDataFetcher;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main (String args[]){

        String jsonPath = "coding_test\\transactions.json\n";
        JsonToEntityMapper mapper = new JsonToEntityMapper();
        List<TransactionEntity> transactions = mapper.deserializeFromJson(jsonPath, TransactionEntity.class);
        JsonBaseRepository jsonBaseRepository = new JsonBaseRepository(transactions);
        TransactionRepository transactionRepository = new TransactionRepository(jsonBaseRepository, transactions);
        MockTransactionRepository mockTransactionRepository = new MockTransactionRepository();
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher(mockTransactionRepository); // Mock the Repository for Unit testing..

    }
}
