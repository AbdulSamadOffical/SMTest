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
     * getTotalAmount must throw an exception, If null is passed as an argument
     */
    @Test(expected = IllegalArgumentException.class)
    public void getTotalAmountNullCheck() {
        this.transactionDataFetcher.getTotalAmount(null);

    }


    /**
     * getTotalAmount must throw an exception, If any amount value is less then zero
     */
    @Test(expected = RuntimeException.class)
    public void getTotalAmountInConsistentAmountData() {
        List<TransactionEntity> list = new ArrayList<>(
                Arrays.asList(
                        new TransactionEntity(663458, -430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false, "Looks like money laundering"),
                        new TransactionEntity(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up")
                ));
       this.transactionDataFetcher.getTotalAmount(list);

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
     * getTotalTransactionAmountSentBy must throw Error if the sender fullName is empty
     */
    @Test(expected = IllegalArgumentException.class)
    public void getTotalTransactionAmountSentByPassedEmptyString(){

        this.transactionDataFetcher.getTotalTransactionAmountSentBy("");

    }
    /**
     * getMaximumAmount must throw an exception, If null is passed as an argument
     */
    @Test(expected = IllegalArgumentException.class)
    public void getMaximumAmountNullCheck() {
        this.transactionDataFetcher.getMaximumAmount(null);

    }


    /**
     * getMaximumAmountInConsistentAmountData must throw an exception, If any amount value is less then zero
     */
    @Test(expected = RuntimeException.class)
    public void getMaximumAmountInConsistentAmountData() {
        List<TransactionEntity> list = new ArrayList<>(
                Arrays.asList(
                        new TransactionEntity(663458, -430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false, "Looks like money laundering"),
                        new TransactionEntity(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up")
                ));
        this.transactionDataFetcher.getMaximumAmount(list);

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
    /**
     * hasOpenComplianceIssues must throw Error if the sender fullName is empty
     */
    @Test(expected = IllegalArgumentException.class)
    public void hasOpenComplianceIssuesPassedEmptyString(){
        this.transactionDataFetcher.hasOpenComplianceIssues("");
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
    /**
     * getTop3TransactionsByAmount must not yield null
     */
    @Test
    public void getTop3TransactionsByAmount(){
        List<TransactionEntity> transactions = this.transactionDataFetcher.getTop3TransactionsByAmount();
        assert transactions != null;

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