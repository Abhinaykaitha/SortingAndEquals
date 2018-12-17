/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import enums.TransactionType;
import exceptions.OverdraftLimitExceededException;
import interfaces.Operations;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class CurrentAccount extends Account{

    public CurrentAccount(Customer customer, long accountNumber) {
        super(customer, accountNumber);
    }

    /**
     *
     * @return a string value for an account in specified format
     */
      @Override
    public String generateStatement() {
        String s = "";
        for (Transaction t : super.getTransactions()) {
            s += t.toString() + "\n";
        }
        String transactionss = s.substring(0, s.length()-1);
        double balance;
        if(super.getBalance()<0){
            balance = 0;
        }else{
            balance = super.getBalance();
        }
        return toString() + "\n-------------------------------------------------------------------------------\n" + 
                "Transaction Type\tTransaction Time\tAmount\tAdditional Charges\tStatus\n" + 
                transactionss + "\n-------------------------------------------------------------------------------\n" +
                "Current Balance: " + String.format("%.2f", balance) + "\nOverdraft usage: $" +
                String.format("%.2f", (OVERDRAFT_LIMIT - overDraft())) + "\t\tOverdraft available: $" + 
                String.format("%.2f", overDraft());

    }
     
    double overDraft() {
        if (super.getBalance() < 0) {
            return super.getBalance() + OVERDRAFT_LIMIT;
        } else {
            return OVERDRAFT_LIMIT;
        }
    }
    @Override
    /**
     * This method takes transaction object as parameter and performs necessary action, based on the transaction type. {double}
     */
     public double makeTransaction(Transaction transaction) {
              double updateBal = 0.0;
        double balanceAvailable = super.getBalance() + OVERDRAFT_LIMIT;
        if (balanceAvailable >= transaction.getAmount()) {
            if (transaction.getTransactionType() == TransactionType.DEPOSIT) {
                transaction.setAdditionalCharges(0.0);
                transaction.setStatus("SUCCESS");
                updateBal = super.getBalance() + transaction.getAmount();
                transactions.add(transaction);
            } else if (transaction.getTransactionType() == TransactionType.ONLINEPURCHASE) {
                transaction.setAdditionalCharges(1.59);
                transaction.setStatus("SUCCESS");
                updateBal = super.getBalance() - (transaction.getAmount() + transaction.getAdditionalCharges());
                transactions.add(transaction);
            } else {
                transaction.setAdditionalCharges(0.0);
                transaction.setStatus("SUCCESS");
                updateBal = super.getBalance() - transaction.getAmount();
                transactions.add(transaction);
            }
        } else {
            if (transaction.getTransactionType() == TransactionType.DEPOSIT) {
                transaction.setAdditionalCharges(0.0);
                transaction.setStatus("SUCCESS");
                updateBal = super.getBalance() + transaction.getAmount();
                transactions.add(transaction);
            } else {
                transaction.setAdditionalCharges(0.0);
                transaction.setStatus("FAILED");
                updateBal = super.getBalance();
                transactions.add(transaction);
                throw new OverdraftLimitExceededException();
            }
        }
        super.setBalance(updateBal);
        return updateBal;
    }
    @Override
    /**
     * This overriding method returns the details of an account.{string}
     */
   
      public String toString() {
        return super.toString() + "\nAccount Type: Current Account\tOverdraft Limit: $" + String.format("%.2f",OVERDRAFT_LIMIT);
    }
}
