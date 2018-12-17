/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class Customer {
    String firstName;
    String lastName;
    String dob;
/**
 * 
 * @param firstName Attribute that stores the first name of the customer {string}
 * @param lastName Attribute that stores the last name of the customer {string}
 * @param dob Attribute that stores the date of birth of the customer {string}
 */
    public Customer(String firstName, String lastName, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
    /**
     * 
     * @return  date of birth of a customer {string}
     */
    public String getDob(){
        return dob;
    }
/**
 * 
 * @return the first name of the customer {string}
 */
    public String getFirstName() {
        return firstName;
    }
/**
 * 
 * @return the last name of the customer {string}
 */
    public String getLastName() {
        return lastName;
    }

    @Override
    /**
     * This overriding method returns the formatted string containing the details of a customer.{string}
     */
   public String toString() {
        return "Name:  " + this.getLastName() + ", " + this.getFirstName() + "\n"
                + "Date of Birth: " + this.getDob() + "\n";
    }
}
