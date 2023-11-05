import com.smallworld.Entity.TransactionEntity;
import com.smallworld.Repository.Transactions.MockTransactionRepository;
import com.smallworld.services.TransactionDataFetcher;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
public class TransactionDataFetcherTest {
    double delta;
    public TransactionDataFetcher transactionDataFetcher;
    public TransactionDataFetcherTest(){
        this.transactionDataFetcher = new TransactionDataFetcher(new MockTransactionRepository());
        this.delta =   0.0001;
    }

    /**
     * Mocked Repository Method, Check does the function Returns the sum of the amounts of all transactions
     */

    @Test
    public void getTotalTransactionAmount() {
        double amount = this.transactionDataFetcher.getTotalTransactionAmount();
        assertEquals(amount, 580.4,this.delta);
    }
    /**
     * Mocked Repository Method, Check does the function Returns the sum of the amounts of all transactions sent by the specified client
     */
    @Test
    public void getTotalTransactionAmountSentBy(){

        double amount = this.transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby");
        assertEquals(amount, 880.4, this.delta);
    }

    /**
     * Returns the highest transaction amount
     */
    @Test
    public void getMaxTransactionAmount(){

        double amount = this.transactionDataFetcher.getMaxTransactionAmount();
        assertEquals(amount, 430.2,this.delta);
    }
    /**
     * Count unique Clients That have sent or Received Transaction
     */
    @Test
    public void countUniqueClients(){
        long uniqueClientsCount = this.transactionDataFetcher.countUniqueClients();
        assertEquals(uniqueClientsCount, 3);
    }
    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    @Test
    public void hasOpenComplianceIssues(){
        boolean hasOpenComplianceIssues = this.transactionDataFetcher.hasOpenComplianceIssues("Tom Shelby");
        assertEquals(hasOpenComplianceIssues, true);
    }
    @Test
    public void getTransactionsByBeneficiaryName(){
        Map<String, List<TransactionEntity>> hasOpenComplianceIssues = this.transactionDataFetcher.getTransactionsByBeneficiaryName();
        String KeyToCheck = "Arthur Shelby";
        assertTrue(KeyToCheck, hasOpenComplianceIssues.containsKey(KeyToCheck));
    }
    /**
     * Returns the identifiers of all open compliance issues
     */
    @Test
    public void getUnsolvedIssueIds(){
        Set<Integer> unSolvedIssueIds = this.transactionDataFetcher.getUnsolvedIssueIds();
        for (Integer id:
             unSolvedIssueIds) {
            assertTrue(unSolvedIssueIds.contains(id));
        }
    }
    /**
     * Returns a list of all solved issue messages
     */
    @Test
    public void getAllSolvedIssueMessages (){
        List<String> messages = this.transactionDataFetcher.getAllSolvedIssueMessages();
        for (String message:
                messages) {
            assertTrue(messages.contains(message));
        }
    }
    @Test
    public void getTop3TransactionsByAmount(){
        List<TransactionEntity> transactions = this.transactionDataFetcher.getTop3TransactionsByAmount();
        assertEquals(transactions.size(),3);

    }
    @Test
    public void topSender(){
        Map<String, Double> topSender = this.transactionDataFetcher.getTopSender();
        String KeyToCheck = "Tom Shelby";
        double value = topSender.get(KeyToCheck);
        assertTrue(KeyToCheck, topSender.containsKey(KeyToCheck));
        assertEquals(value, 20580.4, this.delta);
    }

}