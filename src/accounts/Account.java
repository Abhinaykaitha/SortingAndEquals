/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import interfaces.Operations;
import java.util.ArrayList;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public abstract class Account implements Operations, Comparable<Account> {

    public Customer customer;
    public long accountNumber;
    public double balance;
    public ArrayList<Transaction> transactions;

    /**
     * constructor
     *
     * @param customer {Customer } stores customer object
     * @param accountNumber {long} stores account number
     *
     */
    public Account(Customer customer, long accountNumber) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<Transaction>();

    }

    /**
     * The getter method that returns the Customer object associated with this
     * account.
     *
     * @return customer object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * The getter method that returns the account number.
     *
     * @return account number {long}
     */
    public long getAccountNumber() {
        return accountNumber;
    }

    /**
     * The getter method that returns the balance of an account.
     *
     * @return balance {double}
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @param balance stores balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * This getter method returns all the transactions made by an account as an
     * ArrayList.
     *
     * @return array list of transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    /**
     * This overriding method returns the details of an account.
     */
    public String toString() {
           String detailsString = this.getCustomer().toString()
                + "Account Number: " + this.getAccountNumber();
        return detailsString;
    }//end of to string

   
    @Override
    public abstract double makeTransaction(Transaction transaction) ;

    @Override
    /**
     * generate statement This method generates the statement for an account in
     * a specified format.
     */
public String generateStatement(){
        return toString(); 
    }

    @Override
    public int compareTo(Account a) {
       //return Double.compare(this.gpa, o.getGpa());
       return Long.toString(this.accountNumber).compareTo(Long.toString(a.getAccountNumber()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (int) (this.accountNumber ^ (this.accountNumber >>> 32));
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
        final Account other = (Account) obj;
        if (this.accountNumber != other.accountNumber) {
            return false;
        }
        return true;
    }
    
}
