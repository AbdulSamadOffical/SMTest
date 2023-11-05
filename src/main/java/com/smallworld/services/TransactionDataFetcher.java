package com.smallworld.services;
import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Repository.Interfaces.ITransactionRepository;

import java.util.*;


public class TransactionDataFetcher {
    ITransactionRepository transactionRepository;
    public TransactionDataFetcher(ITransactionRepository  _transactionRepository){
        this.transactionRepository = _transactionRepository;
    }


    private double getTotalAmount(List<TransactionEntity> transactions){
        double totalAmount = 0;
        for (TransactionEntity transaction:
             transactions) {
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
       return this.getTotalAmount(this.transactionRepository.getTotalTransactionAmountSentBy(senderFullName));
    }

    private double getMaximumAmount(List<TransactionEntity>totalUniqueTransactions){
        double maximumAmount = 0;
        for (TransactionEntity transaction:
                totalUniqueTransactions) {
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

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        return transactionRepository.getUniqueSenderAndBeneficiaryClients();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
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

      return this.transactionRepository.getTopSender();
    }
}
