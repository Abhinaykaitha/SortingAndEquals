/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;
import enums.TransactionType;
import exceptions.InsufficentFundsException;
import exceptions.MaxTransactionsException;
import exceptions.OverdraftLimitExceededException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class BankDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, Exception{
  ArrayList<Account> accounts = new ArrayList<>();
        Scanner scan = new Scanner(new File("input.txt"));
        String x = "";
        SavingsAccount savings = null;
        CurrentAccount current = null;
        String accountType = scan.nextLine();
        while (scan.hasNext()) {
            if (!x.isEmpty()) {
                accountType = x;
            }
            String name = scan.nextLine();
            String[] names = name.split(" ");
            System.out.println("------------------------------------------------------------");
            System.out.println("Name of the customer: " + names[0] + "  " + names[1]);
            System.out.println("------------------------------------------------------------");
            String dob = scan.nextLine();
            Customer c1 = new Customer(names[0], names[1], dob);
            long accountNumber = scan.nextLong();
            scan.nextLine();
            if (accountType.equals("savings")) {
                boolean hasWithdrawlLimits = scan.nextBoolean();
                savings = new SavingsAccount(c1, accountNumber, hasWithdrawlLimits);
                scan.nextLine();
            } else {
                current = new CurrentAccount(c1, accountNumber);
            }
            x = scan.nextLine();
            do {
                String transactionInfo = x;
                String[] transactionInfoSplit = transactionInfo.split(" ");
                TransactionType t1;
                switch (transactionInfoSplit[0]) {
                    case "DEPOSIT":
                        t1 = TransactionType.DEPOSIT;
                        break;
                    case "WITHDRAW":
                        t1 = TransactionType.WITHDRAW;
                        break;
                    case "ONLINEPURCHASE":
                        t1 = TransactionType.ONLINEPURCHASE;
                        break;
                    default:
                        t1 = TransactionType.DEPOSIT;
                        break;
                }
                String[] dates = transactionInfoSplit[2].split("-");
                int year = Integer.parseInt(dates[0]);
                int month = Integer.parseInt(dates[1]);
                int day = Integer.parseInt(dates[2]);
                String[] time = transactionInfoSplit[3].split(":");
                int hour = Integer.parseInt(time[0]);
                int mins = Integer.parseInt(time[1]);
                int sec = Integer.parseInt(time[2]);
                LocalDateTime date = LocalDateTime.of(year, month, day, hour, mins, sec);
                Transaction transactions = new Transaction(t1, Double.parseDouble(transactionInfoSplit[1]), date);
                try {
                    if (accountType.equals("savings")) {
                        savings.makeTransaction(transactions);
                        System.out.println("The balance after " + t1 + " in dollars is " + String.format("%.2f", savings.getBalance()));
                    } else {
                        current.makeTransaction(transactions);
                        System.out.println("The balance after " + t1 + " in dollars is " + String.format("%.2f", current.getBalance()));
                    }
                } catch (OverdraftLimitExceededException e) {
                    System.out.println("Exceeded overdraft limit. Avaiable funds including overdraft: " + String.format("%.2f", current.overDraft()));

                } catch (MaxTransactionsException e) {
                    System.out.println("Exceeded number of withdrawals transactions. Number of available withdrawals per month: 6");
                } catch (InsufficentFundsException e) {
                    System.out.println("Insufficent funds. Available funds: " + savings.getBalance());
                }
                if (scan.hasNext()) {
                    x = scan.nextLine();
                } else {
                    break;
                }
            } while (x.contains("DEPOSIT") || x.contains("WITHDRAW") || x.contains("ONLINEPURCHASE"));
            if (accountType.equals("savings")) {
                accounts.add(savings);
            } else {
                accounts.add(current);
            }
        }
        System.out.println("************************************************************************\n"
                + "*********Invoke getNoofWithdrawals() on SavingsAccount objects**********\n"
                + "************************************************************************");
        for (Account a : accounts) {
            if (a.toString().contains("Savings")) {
                SavingsAccount savingsAccount = (SavingsAccount) a;
                System.out.println(a.getCustomer().getFirstName() + " made " + savingsAccount.getNoofWithdrawals() + " withdrawals in this month.");
            }
        }
        System.out.println("***********************************************************************\n"
                + "****Invoke generateStatement() on all objects in accounts ArrayList****\n"
                + "************************************************************************");
        for (Account a : accounts) {
            System.out.println(a.generateStatement());
            System.out.println("************************************************************************");
        }
        System.out.println("*************************Sort on account number*************************\n"
                + "************************************************************************");
        Collections.sort(accounts);
        String limit = "";
        for (Account a : accounts) {
            if (a.toString().contains("Savings")) {
                SavingsAccount savingsAccount = (SavingsAccount) a;
                System.out.println(savingsAccount.toString());
                if (savingsAccount.hasLimitedWithdrawals == true) {
                    limit = "6 Transactions";
                } else {
                    limit = "No limit";
                }
                System.out.println("Transaction Limit for withdrawal: " + limit +"\n-----------------------------------------------------------------------");
            } else {
                CurrentAccount currentAccount = (CurrentAccount) a;
                System.out.println(currentAccount.toString());
                System.out.println("-----------------------------------------------------------------------");

            }
        }
        System.out.println("************************************************************************\n"
                + "*******************Sort on Transaction Time ****************************\n"
                + "************************************************************************");
        for (Account a : accounts) {
            Collections.sort(a.getTransactions());
            String str = a.generateStatement().replace("-------------------------------------------------------------------------------\nTransaction Type\tTransaction Time\tAmount\tAdditional Charges\tStatus\n", "");
            System.out.println(str.substring(0, str.indexOf("\nCurrent Balance")).replace("-------------------------------------------------------------------------------", "-----------------------------------------------------------------------"));
        }
        System.out.println("************************************************************************\n"
                + "************* Sort on Transaction Type& Transaction time ***************\n"
                + "************************************************************************");
        for (Account a : accounts) {
            Collections.sort(a.getTransactions(), new Comparator<Transaction>() {
                @Override
                public int compare(Transaction o1, Transaction o2) {
                    if (o1.getTransactionType().compareTo(o2.getTransactionType()) == 0) {
                        return o1.getTrannsactonTime().compareTo(o2.getTrannsactonTime());
                    } else {
                        return o1.getTransactionType().compareTo(o2.getTransactionType());
                    }
                }
            });
            String str = a.generateStatement().replace("-------------------------------------------------------------------------------\nTransaction Type\tTransaction Time\tAmount\tAdditional Charges\tStatus\n", "");
            System.out.println(str.substring(0, str.indexOf("\nCurrent Balance")).replace("-------------------------------------------------------------------------------", "-----------------------------------------------------------------------"));
        }
        System.out.println("************************************************************************\n"
                + "*************** Sort Accounts based on account balance *****************\n"
                + "************************************************************************");
        Collections.sort(accounts, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return Double.compare(o1.getBalance(), o2.getBalance());
            }
        });
        for (Account a : accounts) {
            if (a.toString().contains("Savings")) {
                SavingsAccount savingsAccount = (SavingsAccount) a;
                System.out.println(a.toString() + "\nTransaction Limit for withdrawal: 6 Transactions" + "\nBalance: " + String.format("%.2f", a.getBalance()) + "\n-----------------------------------------------------------------------");
            } else {
                System.out.println(a.toString() + "\nBalance: " + String.format("%.2f", a.getBalance()) + "\n-----------------------------------------------------------------------");
            }
        }
        
        System.out.println("-----------------------------------------------------------------------\n" +"-------Overrided equals and hashCode methods------");
        System.out.println("Answer for: Equals method checks whether the calling object values are identical compared to the parameter object values.");
        System.out.println("Answer for: Hash method generates hash code for each object and  if the values of two objects are same it does generate same hash code value.");
        System.out.println("Answer for: All test cases are passed, but it fails when the calling object values and parameter object values are different.");
        System.out.println("Answer for: All test cases are passed, but it fails when the calling object values and parameter object values are different while using Equals method.");
    }
}

    
