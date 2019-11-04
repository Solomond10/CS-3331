package bank;

import java.util.*;
import java.io.*;


public class Customer extends Person{
    
    private String checkingAccNum, savingsAccNum, creditAccNum, idNum,
                   checkingBal, savingsBal, creditBal;
    
    
    public Customer(String nFirstName, String nLastName, String nDateOfBirth, 
                    String nIdNum, String nAddress, String nPhoneNumber,
                    String nCheckingAccNum, String nSavingsAccNum, 
                    String nCreditAccNum,String nCheckingBal,String nSavingsBal, 
                    String nCreditBal) {
        
        super(nFirstName, nLastName, nDateOfBirth, nAddress, nPhoneNumber);
        
        firstName = nFirstName;
        lastName = nLastName;
        dateOfBirth = nDateOfBirth; 
        idNum = nIdNum; 
        address  = nAddress; 
        phoneNumber = nPhoneNumber;
        checkingAccNum = nCheckingAccNum;
        savingsAccNum = nSavingsAccNum;
        creditAccNum = nCreditAccNum;        
        checkingBal = nCheckingBal;
        savingsBal = nSavingsBal;
        creditBal = nCreditBal;   
    }
 
    public void Load(Scanner bankFileSc){
        
        String info = bankFileSc.nextLine();
        String[] data = info.split(",");
        firstName = data[0];
        lastName = data[1];
        dateOfBirth = data[2] +","+data[3];
        idNum = data[4];
        address = data[5] + ","+data[6]+","+ data[7]; 
        phoneNumber = data[8];
        checkingAccNum = data[9];
        savingsAccNum = data[10];
        creditAccNum = data[11];
        checkingBal = data[12];
        savingsBal = data[13];
        creditBal = data[14];           
        
    }
    
    public String GetId(){
        return idNum;
    }    
    
    public String GetCheckingAccountNumber(){
        return checkingAccNum;
    }
    
    public String GetSavingsAccountNumber(){
        return savingsAccNum;
    }
 
    public String GetCreditAccountNumber(){
        return creditAccNum;
    }
    
    public String GetCheckingBalance(){
        return checkingBal;
    }
    
    public String GetSavingsBalance(){
        return savingsBal;
    }

    public String GetCreditBalance(){
        return creditBal;
    }    
    
    
}

