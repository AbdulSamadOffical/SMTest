package com.smallworld;

import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Mapper.JsonToEntityMapper;
import com.smallworld.Repository.JsonBaseRepository;
import com.smallworld.Repository.TransactionRepository;
import com.smallworld.services.TransactionDataFetcher;

import java.util.List;

public class Main {

    public static void main (String args[]){
        String jsonPath = "D:\\coding_test(1)\\coding_test\\transactions.json";
        JsonToEntityMapper mapper = new JsonToEntityMapper();
        List<TransactionEntity> transactions = mapper.deserializeFromJson(jsonPath, TransactionEntity.class);


        JsonBaseRepository jsonBaseRepository = new JsonBaseRepository(transactions);
        TransactionRepository transactionRepository = new TransactionRepository(jsonBaseRepository, transactions);
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher(transactionRepository);

        System.out.println(transactionDataFetcher.countUniqueClients());
    }
}
