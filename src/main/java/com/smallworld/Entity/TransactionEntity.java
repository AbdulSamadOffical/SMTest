package com.smallworld.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class TransactionEntity {

    @JsonProperty("mtn")
    private long mtn;
    @JsonProperty("amount")
    private double amount;

    @JsonProperty("senderFullName")
    private String senderFullName;

    @JsonProperty("senderAge")
    private int senderAge;

    @JsonProperty("beneficiaryFullName")
    private String beneficiaryFullName;

    @JsonProperty("beneficiaryAge")
    private int beneficiaryAge;

    @JsonProperty("issueId")
    private int issueId;

    @JsonProperty("issueSolved")
    private boolean issueSolved;

    @JsonProperty("issueMessage")
    private String issueMessage;

    public TransactionEntity() {
        /* This constructor used by jackson serialization*/
    }
    public TransactionEntity(long mtn, double amount, String senderFullName, int senderAge, String beneficiaryFullName
    ,int beneficiaryAge, int issueId, boolean issueSolved, String issueMessage){
        /* This constructor used for creating objects */
        this.mtn = mtn;
        this.amount= amount;
        this.senderFullName = senderFullName;
        this.senderAge = senderAge;
        this.beneficiaryFullName = beneficiaryFullName;
        this.beneficiaryAge = beneficiaryAge;
        this.issueId = issueId;
        this.issueSolved = issueSolved;
        this.issueMessage = issueMessage;
    }


    public long getMtn() {
        return mtn;
    }

    public double getAmount() {
        return amount;
    }
    public static <T, K> Predicate<T> distinctBy(Function<? super T, K> keyExtractor) {
        Set<K> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public boolean isIssueSolved() {
        return issueSolved;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    public void setIssueSolved(boolean issueSolved) {
        this.issueSolved = issueSolved;
    }
    public int getIssueId() {
        return issueId;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "mtn=" + mtn +
                ", amount=" + amount +
                ", senderFullName='" + senderFullName + '\'' +
                ", senderAge=" + senderAge +
                ", beneficiaryFullName='" + beneficiaryFullName + '\'' +
                ", beneficiaryAge=" + beneficiaryAge +
                ", issueId=" + issueId +
                ", issueSolved=" + issueSolved +
                ", issueMessage='" + issueMessage + '\'' +
                '}';
    }

}
