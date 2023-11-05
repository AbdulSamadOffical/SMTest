package com.smallworld;

import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Mapper.JsonToEntityMapper;
import com.smallworld.Repository.JsonBaseRepository;
import com.smallworld.Repository.Transactions.MockTransactionRepository;
import com.smallworld.Repository.Transactions.TransactionRepository;
import com.smallworld.services.TransactionDataFetcher;
import java.util.List;

public class Main {

    public static void main (String args[]){
        String jsonPath = "D:\\SWF\\coding_test(1)\\coding_test\\transactions.json";
        JsonToEntityMapper mapper = new JsonToEntityMapper();
        List<TransactionEntity> transactions = mapper.deserializeFromJson(jsonPath, TransactionEntity.class);
        JsonBaseRepository jsonBaseRepository = new JsonBaseRepository(transactions);
        TransactionRepository transactionRepository = new TransactionRepository(jsonBaseRepository, transactions);
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher(transactionRepository);

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Sum of the amounts of all transactions:");
        System.out.println(transactionDataFetcher.getTotalTransactionAmount());
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Sum of the amounts of all transactions sent by the specified client:");
        System.out.println(transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby"));
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Highest transaction amount");
        System.out.println(transactionDataFetcher.getMaxTransactionAmount());
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Counts the number of unique clients that sent or received a transaction");
        System.out.println(transactionDataFetcher.countUniqueClients());
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Returns whether a client (sender or beneficiary) has at least one transaction with a compliance issue that has not been solved");
        System.out.println(transactionDataFetcher.hasOpenComplianceIssues("Tom Shelby"));
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Returns all transactions indexed by beneficiary name");
        System.out.println(transactionDataFetcher.getTransactionsByBeneficiaryName());
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Identifiers of all open compliance issues i.e. Issue Id's");
        System.out.println(transactionDataFetcher.getUnsolvedIssueIds());
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("List of all solved issue messages, Null messages can be filtered if any.");
        System.out.println(transactionDataFetcher.getAllSolvedIssueMessages());
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("The 3 transactions with highest amount sorted by amount descending");
        System.out.println(transactionDataFetcher.getTop3TransactionsByAmount());
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Sender with the most total sent amount");
        System.out.println(transactionDataFetcher.getTopSender());
    }
}
