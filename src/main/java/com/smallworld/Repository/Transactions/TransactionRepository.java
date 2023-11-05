package com.smallworld.Repository.Transactions;


import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Repository.Interfaces.IBaseRepository;
import com.smallworld.Repository.Interfaces.ITransactionRepository;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionRepository  implements ITransactionRepository {
    IBaseRepository<TransactionEntity> baseRepository;
    List<TransactionEntity> datasource;
    public TransactionRepository(IBaseRepository<TransactionEntity> _baserepository, List<TransactionEntity> _datasource){

        this.baseRepository = _baserepository;
        this.datasource = _datasource;
    }


    @Override
    public List<TransactionEntity> getTotalUniqueTransactions() {
        return datasource.stream()
                .filter(TransactionEntity.distinctBy(TransactionEntity::getMtn))
                .toList();

    }
    @Override
    public List<TransactionEntity> getTotalTransactionAmountSentBy(String senderFullName) {
        List<TransactionEntity> getTotalUniqueTransactions = this.getTotalUniqueTransactions();

        return getTotalUniqueTransactions.stream()
                .filter(getTotalUniqueTransaction -> getTotalUniqueTransaction.getSenderFullName().equals(senderFullName))
                .toList();

    }
    public boolean isSenderAndBeneficiaryNameMatched (TransactionEntity uniqueTransaction, TransactionEntity transaction){
        return uniqueTransaction.getSenderFullName().equals(transaction.getSenderFullName())
                && uniqueTransaction.getBeneficiaryFullName().equals(transaction.getBeneficiaryFullName());
    }


    @Override
    public int getUniqueSenderAndBeneficiaryClients(){

        Set<String> uniqueClients = new HashSet<>();

        for (TransactionEntity transaction:
             this.getTotalUniqueTransactions()) {
            uniqueClients.add(transaction.getSenderFullName());
            uniqueClients.add(transaction.getBeneficiaryFullName());
        }

        System.out.println(uniqueClients);
        return uniqueClients.size();
    }

    public boolean hasOpenComplianceIssues(String clientFullName){


        return this.datasource.stream()
                .anyMatch(transaction ->
                        (transaction.getSenderFullName().equals(clientFullName) ||
                                transaction.getBeneficiaryFullName().equals(clientFullName)) &&
                                !transaction.isIssueSolved() &&
                                !Objects.isNull(transaction.getIssueId())
                );

    }

    @Override
    public Map<String, List<TransactionEntity>> getTransactionsByBeneficiaryName() {
        return this.datasource.stream()
                .collect(Collectors.groupingBy(TransactionEntity::getBeneficiaryFullName));
    }
    @Override
    public Set<Integer> getUnsolvedIssueIds(){
        Set<Integer> getUnsolvedIssueIds = new HashSet<>();


        for (TransactionEntity transactionEntity:
             this.datasource) {
            if(!transactionEntity.isIssueSolved()){
                getUnsolvedIssueIds.add(transactionEntity.getIssueId());
            }
        }
        return getUnsolvedIssueIds;
    }

    public List<String> getAllSolvedIssueMessages(){
        List<String> getAllSolvedIssueMessages = new ArrayList<>();


        for (TransactionEntity transactionEntity:
                this.datasource) {
            if(transactionEntity.isIssueSolved()){
                getAllSolvedIssueMessages.add(transactionEntity.getIssueMessage());
            }
        }
        return getAllSolvedIssueMessages;
    }
    public List<TransactionEntity> getTop3TransactionsByAmount(){
        return  this.getTotalUniqueTransactions().stream()
                .sorted(Comparator.comparing(TransactionEntity::getAmount).reversed()) // Sort in descending order based on the "value" property
                .limit(3)
                .collect(Collectors.toList());
    }


    @Override
    public Map<String, List<TransactionEntity>> getTransactionBySenderFullName() {
        return this.getTotalUniqueTransactions().stream()
                .collect(Collectors.groupingBy(TransactionEntity::getSenderFullName));
    }

    public Map<String, Double> getTopSender() {

        Map<String, Double> dict = new HashMap<>();
        dict.put("senderSum", 0.0);
        dict.put("maxSenderSum", 0.0);

        Map<String, Double> topSender = new HashMap<>();

        Map<String, List<TransactionEntity>> indexBySenderName= this.getTransactionBySenderFullName();

        indexBySenderName.forEach((senderName, group) -> {
            group.forEach((list) -> {

                double sum = dict.get("senderSum");
                sum = sum + list.getAmount();
                dict.put("senderSum",sum);

            });
            double sum = dict.get("senderSum");
            if(sum > dict.get("maxSenderSum")){

                dict.put("maxSenderSum",sum);
                topSender.clear();
                topSender.put(senderName,sum);
            }
            dict.put("senderSum",0.0);
        });
        return topSender;
    }
}
