package com.smallworld.Repository.Transactions;

import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Repository.Interfaces.ITransactionRepository;
import java.util.*;

public class MockTransactionRepository implements ITransactionRepository {

    @Override
    public List<TransactionEntity> getTotalUniqueTransactions() {
        return  new ArrayList<>(
                Arrays.asList(
                        new TransactionEntity(663458, 430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false, "Looks like money laundering"),
                        new TransactionEntity(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up")
                )
        );
    }

    @Override
    public List<TransactionEntity> getTotalTransactionAmountSentBy(String senderFullName) {
        return  new ArrayList<>(
                Arrays.asList(
                        new TransactionEntity(663458, 430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false, "Looks like money laundering"),
                        new TransactionEntity(1284564, 450.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up")
                )
        );
    }



    @Override
    public boolean hasOpenComplianceIssues(String clientFullName) {
        return true;
    }

    @Override
    public Map<String, List<TransactionEntity>> getTransactionsByBeneficiaryName() {
        Map<String, List<TransactionEntity>> getTransactionsByBeneficiaryName = new HashMap<>();
        getTransactionsByBeneficiaryName.put("Arthur Shelby",  Arrays.asList(
                new TransactionEntity(663458, 430.2, "Tom Shelby", 22, "Arthur Shelby", 33, 1, false, "Looks like money laundering"),
                new TransactionEntity(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up")
        ));
        return getTransactionsByBeneficiaryName;
    }

    @Override
    public Set<Integer> getUnsolvedIssueIds() {
        List<Integer> intList = Arrays.asList(1, 3, 99, 54, 15);

        return new HashSet<>(intList);

    }

    @Override
    public List<String> getAllSolvedIssueMessages() {
        String[] messages = {
                "Never gonna give you up",
                "Never gonna let you down",
                "Never gonna run around and desert you",
        };

        return  Arrays.asList(messages);

    }

    @Override
    public List<TransactionEntity> getTop3TransactionsByAmount() {
        return  new ArrayList<>(
                Arrays.asList(
                        new TransactionEntity(5465465, 985.0, "Arthur Shelby", 60, "Ben Younger", 47, 15, false, "Something's fishy"),
                        new TransactionEntity(32612651, 666.0, "Grace Burgess", 31, "Michael Gray", 58, 54, false, "Something ain't right"),
                        new TransactionEntity(663458, 430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false, "Looks like money laundering")
                )
        );
    }

    @Override
    public Map<String, List<TransactionEntity>> getTransactionBySenderFullName() {
        Map<String, List<TransactionEntity>> getTransactionsBySenderFullName = new HashMap<>();
        getTransactionsBySenderFullName.put("Tom Shelby",  Arrays.asList(
                new TransactionEntity(663458, 430.2, "Tom Shelby", 22, "Arthur Shelby", 33, 1, false, "Looks like money laundering"),
                new TransactionEntity(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up"),
        new TransactionEntity(5465465, 20000, "Arthur Shelby", 60, "Ben Younger", 47, 15, false, "Something's fishy")
        ));
        return getTransactionsBySenderFullName;
    }

}
