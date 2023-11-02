package com.smallworld.Repository.Interfaces;

import com.smallworld.Entity.TransactionEntity;

import java.util.List;

public interface ITransactionRepository {
    List<TransactionEntity> getTotalUniqueTransactions();
    List<TransactionEntity> getTotalTransactionAmountSentBy(String senderFullName);
    List<TransactionEntity> getUniqueSenderAndBeneficiaryClients();

    boolean hasOpenComplianceIssues(String clientFullName);


}