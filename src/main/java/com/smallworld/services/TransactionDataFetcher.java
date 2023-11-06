package com.smallworld.services;
import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Repository.Interfaces.ITransactionRepository;

import java.util.*;


public class TransactionDataFetcher {
    ITransactionRepository transactionRepository;
    public TransactionDataFetcher(ITransactionRepository  _transactionRepository){
        this.transactionRepository = _transactionRepository;
    }


    public double getTotalAmount(List<TransactionEntity> transactions){
        if(transactions == null){
            throw new IllegalArgumentException("List cannot be null");
        }

        double totalAmount = 0;
        for (TransactionEntity transaction:
             transactions) {
            if (transaction.getAmount() <= 0) {
                throw new RuntimeException("Amount must be greater than zero for transaction: " + transaction.getMtn());
            }
            totalAmount = totalAmount + transaction.getAmount();
        }
        return totalAmount;
    }

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
        return this.getTotalAmount(this.transactionRepository.getTotalUniqueTransactions());
    }
    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName){
        if(senderFullName.isEmpty()){
            throw new IllegalArgumentException("Sender FullName cannot be Empty");
        }
       return this.getTotalAmount(this.transactionRepository.getTotalTransactionAmountSentBy(senderFullName));
    }


    public double getMaximumAmount(List<TransactionEntity>totalUniqueTransactions){
        if(totalUniqueTransactions == null){
            throw new IllegalArgumentException("List cannot be null");
        }
        double maximumAmount = 0;
        for (TransactionEntity transaction:
                totalUniqueTransactions) {
            if (transaction.getAmount() <= 0) {
                throw new RuntimeException("Amount must be greater than zero for transaction: " + transaction.getMtn());
            }

            if(transaction.getAmount() > maximumAmount){
                maximumAmount = transaction.getAmount();
            }
        }
        return maximumAmount;
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {

        List<TransactionEntity> totalUniqueTransactions = this.transactionRepository.getTotalUniqueTransactions();
        return this.getMaximumAmount(totalUniqueTransactions);
    }

    public int getUniqueSenderAndBeneficiaryClients(){

        Set<String> uniqueClients = new HashSet<>();


        for (TransactionEntity transaction:
                this.transactionRepository.getTotalUniqueTransactions()) {
            uniqueClients.add(transaction.getSenderFullName());
            uniqueClients.add(transaction.getBeneficiaryFullName());
        }

        return uniqueClients.size();
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        return this.getUniqueSenderAndBeneficiaryClients();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        if(clientFullName.isEmpty()){
            throw new IllegalArgumentException("Client FullName cannot be Empty");
        }
        return transactionRepository.hasOpenComplianceIssues(clientFullName);
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, List<TransactionEntity>> getTransactionsByBeneficiaryName() {
        return transactionRepository.getTransactionsByBeneficiaryName();
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        return this.transactionRepository.getUnsolvedIssueIds();
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        return this.transactionRepository.getAllSolvedIssueMessages();
    }

    /**
     * Returns the 3 transactions with highest amount sorted by amount descending
     */
    public List<TransactionEntity> getTop3TransactionsByAmount() {
        return this.transactionRepository.getTop3TransactionsByAmount();
    }

    /**
     * Returns the sender with the most total sent amount
     */
    public Map<String, Double> getTopSender() {

            Map<String, Double> dict = new HashMap<>();
            dict.put("senderSum", 0.0);
            dict.put("maxSenderSum", 0.0);

            Map<String, Double> topSender = new HashMap<>();

            Map<String, List<TransactionEntity>> indexBySenderName= this.transactionRepository.getTransactionBySenderFullName();
            if(indexBySenderName == null){
                throw new RuntimeException("Top sender does not exists in the system");
            }
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

