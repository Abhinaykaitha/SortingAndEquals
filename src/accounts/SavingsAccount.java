/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import enums.TransactionType;
import exceptions.InsufficentFundsException;
import exceptions.MaxTransactionsException;
import interfaces.Operations;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class SavingsAccount extends Account {
    public boolean hasLimitedWithdrawals;
/**
 * 
 * @param customer customer details
 * @param accountNumber customer account number
 * @param hasLimitedWithdrawals An attribute that stores a boolean flag to determine if there is a limit on number of withdrawals within a month.
 */
    public SavingsAccount( Customer customer, long accountNumber,boolean hasLimitedWithdrawals) {
        super(customer, accountNumber);
        this.hasLimitedWithdrawals = hasLimitedWithdrawals;
    }
    @Override
    /**
     * This overriding method returns the details of an account.{string}
     */
    public String toString() {
        return super.toString() + "\nAccount Type: Savings Account\tInterest Rate: "+String.format("%.2f", Operations.SAVING_INTEREST) + "%\nTransaction Limit for withdrawal: 6 Transactions.";
    }
    @Override
    /**
     * This method generates the statement for an account in a specified format.{string}
     */
   public String generateStatement() {
        String transationsss = "";
        for (Transaction t1 : super.getTransactions()) {
            transationsss += t1.toString() + "\n";
        }
        String transactionss = transationsss.substring(0, transationsss.length() - 1);
        return toString() +  "\n-------------------------------------------------------------------------------\n" + 
                "Transaction Type\tTransaction Time\tAmount\tAdditional Charges\tStatus\n" + 
                transactionss +  "\n-------------------------------------------------------------------------------\n" + "Current Balance: " + String.format("%.2f", super.getBalance()) + 
                "\t\tInterest: $" + String.format("%.2f", super.getBalance() * (SAVING_INTEREST / 100));
    }


    /**
     * 
     * @return withdrawal transactions made by the customer in the current month
     */
    public int getNoofWithdrawals(){
        int noOfWithdrawls = 0;
        LocalDateTime currentDate = LocalDateTime.now();
        Month currentMonth = currentDate.getMonth();
        int currentYear = currentDate.getYear();
        for (Transaction t1 : super.getTransactions()) {
            if (t1.getTrannsactonTime().getMonth() == currentMonth && t1.getTrannsactonTime().getYear() == currentYear) {
                if (t1.getTransactionType() == TransactionType.WITHDRAW) {
                    noOfWithdrawls += 1;
                }
            }
        }

        return noOfWithdrawls;
    }
    /**
     * 
     * @param transaction 
     * @return performs necessary action, based on the transaction type {double}
     * @throws java.lang.Exception
     * @throws InsufficentFundsException
     * @throws MaxTransactionsException 
     */
    @Override
    public double makeTransaction(Transaction transaction)  {
        double updatedBalance = 0.0;
        if (transaction.getTransactionType() == TransactionType.DEPOSIT) {
            transaction.setAdditionalCharges(0.0);
            transaction.setStatus("SUCCESS");
            updatedBalance = super.getBalance() + transaction.getAmount();
            transactions.add(transaction);
        } else if (transaction.getTransactionType() == TransactionType.ONLINEPURCHASE) {
            if (transaction.getAmount() < super.getBalance()) {
                transaction.setAdditionalCharges(1.99);
                transaction.setStatus("SUCCESS");
                updatedBalance = super.getBalance() - (transaction.getAmount() + transaction.getAdditionalCharges());
                transactions.add(transaction);
            } else {
                transaction.setAdditionalCharges(0.0);
                transaction.setStatus("FAILED");
                updatedBalance = super.getBalance();
                transactions.add(transaction);
                throw new InsufficentFundsException();
            }
        } else {
            if (transaction.getAmount() <= super.getBalance() && hasLimitedWithdrawals == false) {
                if (getNoofWithdrawals() <= 6) {
                    transaction.setAdditionalCharges(0.0);
                    transaction.setStatus("SUCCESS");
                    updatedBalance = super.getBalance() - transaction.getAmount();
                    transactions.add(transaction);
                } else if (getNoofWithdrawals() > 6) {
                    if (0.01 * transaction.getAmount() >= 2.59) {
                        transaction.setAdditionalCharges(0.01 * transaction.getAmount());
                    } else {
                        transaction.setAdditionalCharges(2.59);
                    }
                    transaction.setStatus("SUCCESS");
                    updatedBalance = super.getBalance() - (transaction.getAmount() + transaction.getAdditionalCharges());
                    transactions.add(transaction);
                }
            } else if (transaction.getAmount() <= super.getBalance() && hasLimitedWithdrawals == true) {
                if (getNoofWithdrawals() <= 5) {
                    transaction.setAdditionalCharges(0.0);
                    transaction.setStatus("SUCCESS");
                    updatedBalance = super.getBalance() - transaction.getAmount();
                    transactions.add(transaction);
                } else if (getNoofWithdrawals() > 5) {
                    transaction.setAdditionalCharges(0.0);
                    transaction.setStatus("FAILED");
                    updatedBalance = super.getBalance();
                    transactions.add(transaction);
                    throw new MaxTransactionsException();
                }
            } else if (transaction.getAmount() > super.getBalance()) {
                transaction.setAdditionalCharges(0.0);
                transaction.setStatus("FAILED");
                updatedBalance = super.getBalance();
                transactions.add(transaction);
                throw new InsufficentFundsException();
            }
        }
        super.setBalance(updatedBalance);
        return updatedBalance;
    }
   
     
}
