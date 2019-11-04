package bank;

import java.util.Scanner;
import java.io.*;

public abstract class Account {

    public Account(){
    }  
    public abstract void InquireBalance(Customer [] aCustomer, 
                                        int numOfCustomers,int size,
                                        FileWriter fw) 
                                        throws IOException;
    
    public abstract void DepositMoney(Customer [] aCustomer, int numOfCustomers, 
                                      FileWriter fw) throws IOException ;
    
    public abstract void WithdrawMoney(Customer [] aCustomer, int numOfCustomers,
                                       FileWriter fw) throws IOException;
    
    public abstract void TransferMoney(Customer [] aCustomer, int numOfCustomers,
                                       FileWriter fw) throws IOException;
    
    public abstract void PaySomeone(Customer [] aCustomer, int numOfCustomers,
                                    FileWriter fw) throws IOException;

}

