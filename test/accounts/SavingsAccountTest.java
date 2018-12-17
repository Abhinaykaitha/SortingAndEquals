/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;
import enums.TransactionType;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class SavingsAccountTest {
    
    public SavingsAccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class SavingsAccount.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
       TransactionType t = TransactionType.WITHDRAW;
        String tran2 = "2018-11-07";
        String tranTime2 = "12:00:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(tran2 + " " + tranTime2, format);
        Transaction trans2 = new Transaction(TransactionType.DEPOSIT,10000.00,dateTime);
        Transaction trans = new Transaction(t,100.00,dateTime);
        Customer customer = new Customer("abhinay","kaitha","07/11/1997");
        long num = 1234567890L;
        long num1 = 1234567890L;
        SavingsAccount acc = new SavingsAccount(customer,num,true);
        acc.transactions.add(trans);
        String expResult = "Name:  kaitha, abhinay\n" +
"Date of Birth: 07/11/1997\n" +
"Account Number: 1234567890\n" +
"Account Type: Savings Account	Interest Rate: 5.80%\n" +
"Transaction Limit for withdrawal: 6 Transactions.";
        String result = acc.toString();
        //System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of generateStatement method, of class SavingsAccount.
     */
    @Test
    public void testGenerateStatement() throws Exception {
            System.out.println("generateStatement");
        Customer customer = new Customer("abhinay", "kaitha", "07/11/1997");
        LocalDateTime date = LocalDateTime.of(2018, Month.DECEMBER, 01, 12, 12, 12);
        Transaction t1 = new Transaction(TransactionType.DEPOSIT, 100.0, date);
        SavingsAccount instance = new SavingsAccount(customer, 2651212, true);
        Transaction t2 = new Transaction(TransactionType.WITHDRAW, 10.0, date);
        instance.makeTransaction(t1);
        instance.makeTransaction(t2);
        String expResult = "Name:  kaitha, abhinay\nDate of Birth: 07/11/1997\nAccount Number: 2651212\nAccount Type: Savings Account\tInterest Rate: 5.80%\nTransaction Limit for withdrawal: 6 Transactions.\n-------------------------------------------------------------------------------\nTransaction Type\tTransaction Time\tAmount\tAdditional Charges\tStatus\nDEPOSIT\t\t\t2018-12-01T12:12:12\t$100.00\t\t$0.00\t\tSUCCESS\nWITHDRAW\t\t2018-12-01T12:12:12\t$10.00\t\t$0.00\t\tSUCCESS\n-------------------------------------------------------------------------------\nCurrent Balance: 90.00\t\tInterest: $5.22";
        String result = instance.generateStatement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNoofWithdrawals method, of class SavingsAccount.
     */
    @Test
    public void testGetNoofWithdrawals() {
        TransactionType t = TransactionType.WITHDRAW;
        String tran2 = "2018-11-07";
        String tranTime2 = "12:00:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(tran2 + " " + tranTime2, format);
        Transaction trans2 = new Transaction(TransactionType.DEPOSIT,10000.00,dateTime);
        Transaction trans = new Transaction(t,100.00,dateTime);
        Customer customer = new Customer("abhinay","kaitha","07/11/1997");
        long num = 1234567890L;
        long num1= 1234567890L;
        SavingsAccount acc = new SavingsAccount(customer,num,true);
        acc.transactions.add(trans);
        acc.transactions.add(trans2);
        int expResult = 1;
        int result = acc.getNoofWithdrawals();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of makeTransaction method, of class SavingsAccount.
     */
    @Test
    public void testMakeTransaction() throws Exception {
        System.out.println("makeTransaction");
      TransactionType t = TransactionType.WITHDRAW;
        String tran2 = "2018-11-07";
        String tranTime2 = "12:00:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(tran2 + " " + tranTime2, format);
        Transaction trans2 = new Transaction(TransactionType.DEPOSIT,10000.00,dateTime);
        Transaction trans = new Transaction(t,100.00,dateTime);
        Customer customer = new Customer("abhinay","kaitha","07/11/1997");
        long num = 1234567890L;
        long num1 = 1234567890L;
        SavingsAccount acc = new SavingsAccount(customer,num,true);
        double expResult = 10000.0;
        double result = acc.makeTransaction(trans2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
