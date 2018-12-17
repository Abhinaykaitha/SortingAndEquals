/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import accounts.Transaction;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public interface Operations {
    double OVERDRAFT_LIMIT =500.00;
    double SAVING_INTEREST=5.8;
     
   String generateStatement();
   double makeTransaction(Transaction transaction) ;
       
     
           
    
}
