package com.smallworld.Repository.Interfaces;

import com.smallworld.Entity.TransactionEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ITransactionRepository {
    List<TransactionEntity> getTotalUniqueTransactions();
    List<TransactionEntity> getTotalTransactionAmountSentBy(String senderFullName);

    boolean hasOpenComplianceIssues(String clientFullName);
    Map<String, List<TransactionEntity>> getTransactionsByBeneficiaryName();
    Set<Integer> getUnsolvedIssueIds();
    List<String> getAllSolvedIssueMessages();
    List<TransactionEntity> getTop3TransactionsByAmount();
    Map<String, List<TransactionEntity>> getTransactionBySenderFullName();

}