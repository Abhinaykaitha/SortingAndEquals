/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

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
public class CustomerTest {
    
    public CustomerTest() {
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
     * Test of getDob method, of class Customer.
     */
    @Test
    public void testGetDob() {
        System.out.println("getDob");
        Customer instance = new Customer("abhinay","kaitha","07/11/1997");
        String expResult = "07/11/1997";
        String result = instance.getDob();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class Customer.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Customer instance = new Customer("abhinay","kaitha","07/11/1997");
        Customer instance1 = new Customer("abhi","kaitha","07/11/1997");
        String expResult = "abhinay";
        String result = instance.getFirstName();
        String result1 = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class Customer.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Customer instance = new Customer("abhinay","kaitha","07/11/1997");
        String expResult = "kaitha";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Customer.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Customer instance = new Customer("abhinay","kaitha","07/11/1997");
        String expResult = "Name:  kaitha, abhinay\n" +
"Date of Birth: 07/11/1997\n";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
