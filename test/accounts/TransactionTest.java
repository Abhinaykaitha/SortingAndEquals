/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import enums.TransactionType;
import java.time.LocalDateTime;
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
public class TransactionTest {
    private Transaction trans;
    private Transaction trans2;
    public TransactionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       TransactionType t = TransactionType.DEPOSIT;
        String tran2 = "2018-10-07";
        String tranTime2 = "12:00:00";
        String tran = "2018-10-08";
        String tranTime = "12:00:01";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(tran2 + " " + tranTime2, fmt);
        LocalDateTime dateTime2 = LocalDateTime.parse(tran + " " + tranTime, fmt);
        Transaction instance = new Transaction(t,10000.00,dateTime);
        Transaction instance2 = new Transaction(t,8000.00,dateTime2);
        this.trans = instance;
        this.trans2 = instance2;
    }
    
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAdditionalCharges method, of class Transaction.
     */
    @Test
    public void testGetAdditionalCharges() {
        System.out.println("getAdditionalCharges");
        //Transaction instance = null;
        double expResult = 0.0;
        double result = this.trans.getAdditionalCharges();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class Transaction.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Transaction instance = this.trans;
        instance.status = "SUCCESS";
        String expResult = "SUCCESS";
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTransactionType method, of class Transaction.
     */
    @Test
    public void testGetTransactionType() {
     System.out.println("getTransactionType");
        Transaction instance = this.trans;
        TransactionType expResult = TransactionType.DEPOSIT;
        TransactionType result = instance.getTransactionType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAmount method, of class Transaction.
     */
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
       // Transaction instance = null;
        double expResult = 10000.0;
        double result = this.trans.getAmount();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getTrannsactonTime method, of class Transaction.
     */
    @Test
    public void testGetTrannsactonTime() {
               System.out.println("getTrannsactonTime");
        Transaction instance = this.trans;
        String tran = "2018-10-07";
        String tranTime = "12:00:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(tran + " " + tranTime, format);
        LocalDateTime expResult = dateTime;
        LocalDateTime result = instance.getTrannsactonTime();
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setAdditionalCharges method, of class Transaction.
     */
    @Test
    public void testSetAdditionalCharges() {
        System.out.println("setAdditionalCharges");
        double additionalCharges = 7.0;
        Transaction instance = this.trans;
        instance.setAdditionalCharges(additionalCharges);
        double expResult = 7.0;
        double result = instance.getAdditionalCharges();
        assertEquals(expResult, result,0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class Transaction.
     */
    @Test
    public void testSetStatus() {
       System.out.println("setStatus");
        String status = "FAILED";
        Transaction instance = this.trans;
        instance.setStatus(status);
        String expResult = status ;
        String result = instance.getStatus();
        assertEquals(expResult, result,"FAILED");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Transaction.
     */
  /* @Test
    public void testToString() {
           System.out.println("toString");
        Transaction instance = this.trans;
        String expResult = "DEPOSIT              2018-10-07T12:00:00        $10000.00        $0.00       null";
        String result = instance.toString();
        //System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/

    /**
     * Test of compareTo method, of class Transaction.
     */
    @Test
    public void testCompareTo() {
         
        System.out.println("compareTo");
        Transaction t = this.trans;
        Transaction instance = this.trans;
        int expResult = 0;
        int result = instance.compareTo(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Transaction.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        TransactionType t = TransactionType.ONLINEPURCHASE;
        System.out.println("hashCode");
        String tranDate = "2018-10-07";
        String tranTime = "12:00:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(tranDate + " " + tranTime, format);
        Transaction instance = new Transaction(t,10000.00,dateTime);;
        int expResult = 1217967978;
        int result = instance.hashCode();
        System.out.println(result);
        assertEquals(expResult, result);
        //-193543818
       // System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of equals method, of class Transaction.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
       TransactionType t = TransactionType.DEPOSIT;
        System.out.println("equals"); 
        String tranDate = "2018-10-07";
        String tranTime = "08:42:03";
        String tran2 = "2018-10-08";
        String tranTime2 = "12:00:01";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(tranDate + " " + tranTime, format);
        LocalDateTime dateTime2 = LocalDateTime.parse(tran2 + " " + tranTime2, format);
        Object obj = new Transaction(t,10000.00,dateTime);
        Transaction instance = new Transaction(t,10000.00,dateTime2);;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
    

