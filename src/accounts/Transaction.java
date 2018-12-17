/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import enums.TransactionType;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class Transaction implements Comparable<Transaction> {
     public double additionalCharges;

     public String status;
  
     public TransactionType transactionType;
     public double amount;
     public LocalDateTime transactionTime;

   /**
    * 
    * @param transactionType stores type of transaction 
    * @param amount stores the amount for a transaction
    * @param transactionTime  stores the time and date of this transaction 
    */
    public Transaction(TransactionType transactionType, double amount, LocalDateTime transactionTime) {
        this.amount = amount;
        this.transactionTime = transactionTime;
        this.transactionType = transactionType;
    }
/**
 * 
 * @return the additional charges {double}
 */
    public double getAdditionalCharges() {
        return additionalCharges;
    }
/**
 * 
 * @return status of a transaction.
 */
    public String getStatus() {
        return status;
    }
/**
 * 
 * @return  type of transaction 
 */
    public TransactionType getTransactionType() {
        return transactionType;
    }
/**
 * 
 * @return  the amount 
 */
    public double getAmount() {
        return amount;
    }
/**
 * 
 * @return  the timestamp of a transaction.
 */
    public LocalDateTime getTrannsactonTime() {
        return transactionTime;
    }
    
/**
 * 
 * @param additionalCharges additional charges involved in the transaction
 */
    public void setAdditionalCharges(double additionalCharges) {
        this.additionalCharges = additionalCharges;
    }
/**
 * 
 * @param status sets the value of instance variable status
 */
    public void setStatus(String status) {
        this.status = status;
    }
     /**
      * 
      * @return transaction details
      */
     @Override

      public String toString() {
        if (transactionType == TransactionType.DEPOSIT) {
            return transactionType + "\t\t\t" + transactionTime + "\t$" + String.format("%.2f", amount) + "\t\t$" + 
                    String.format("%.2f", additionalCharges) + "\t\t" + status;
        } else {
            return transactionType + "\t\t" + transactionTime + "\t$" + String.format("%.2f", amount) + "\t\t$" + 
                    String.format("%.2f", additionalCharges) + "\t\t" + status;
        }
    }
     @Override
      public int compareTo(Transaction t){
          if(this.transactionTime.equals(t.transactionTime)){
              return 0;
          }else if(this.transactionTime.isAfter(t.transactionTime)){
              return 1;
      }else{
              return -1;
          }
      }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.transactionTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (!Objects.equals(this.transactionTime, other.transactionTime)) {
            return false;
        }
        return true;
    }
     
        
    
}
