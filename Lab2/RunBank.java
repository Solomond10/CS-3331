package bank;

import java.util.*;
import java.io.*;


public class RunBank {

    /*  Programmer: Solomon Davis
        Alias: Spiderman
        Due Date: Sunday, October 6, 2019
        Course: Advanced Object Oriented Programming (CS 3331)
        Instructor: Daniel Meija
        Lab Assignment: Programming Assignment 2
        Lab Description: In this lab you are organizing a bank that must allow
                         customers to inquire a balance, pay someone, deposit
                         money, withdraw money, and log all transactions. Logged
                         tranactions will be written to a text file.
        Honesty Statement: I can confirm that the work of this assignment is 
                           completely my own. By turning in this assignment, I
                           declare that I not recieve unauthorized assistance.
                           Moreover, all deliverables including, but not limited
                           to the source code, lab report and outpuut files were
                           written and produced by me alone.

     */

    public static void main(String[] args)  throws IOException{

        char userChoice1 = 'Z',userChoice2 ='Z';
        int size = 0, numOfCustomers = 0;
        Customer [] aCustomer;
        PrintWriter pw;
        pw = new PrintWriter("Logs.txt");
        FileWriter fw;
        File logFile = new File("Logs.txt");
        fw = new FileWriter(logFile,true); 
        
        
        size = GetNumberOfAccounts(size);
        aCustomer = new Customer[size];
        LoadAccountInfo(aCustomer,size);
        numOfCustomers = size - 1;
        
          
        while(userChoice1 != 'q' && userChoice1 != 'Q' ){
            
            userChoice1 = GetUserChoice1(userChoice1);
            
            switch(userChoice1){
            
                case 'A':
                case 'a':
                    userChoice2 = GetUserChoice2(userChoice2);
                    DecideIfThePersonIsAManager(userChoice2,aCustomer,
                                                numOfCustomers,fw,size);
                    break;
                    
                case 'B':
                case 'b':
                    PerformMultiplePeopleChoice(aCustomer, numOfCustomers,fw);
                    break;
                    
                case 'Q':
                case 'q':
                    break;
                    
                default:
                    System.out.println("Not a choice");
                    
            }
        }
        
        fw.close();
    }     
    
    
    public static int GetNumberOfAccounts(int size) throws IOException{
        
        String nameDescriptions,info;
    
        File bankFile = new File("CS 3331 - Bank Users 2.csv");
        //File bankFile = new File(/*"CS 3331 - Bank Users 2.csv"*/"CS 3331 - Bank Users 3a(2).csv");
        Scanner bankSc = new Scanner(bankFile);
        String nameDescription = bankSc.nextLine();
        
        
        
        while(bankSc.hasNext()){
            info = bankSc.nextLine();
            String[] sp = info.split(",");
            size++;
        }
        
        
        
        bankSc.close();
        return size;
        
    }
    
    public static void LoadAccountInfo(Customer [] aCustomer, int size) 
                                                    throws IOException{
    
        int numOfCustomers = 0;
        File bankFile = new File("CS 3331 - Bank Users 2.csv");
        Scanner bankFileSc = new Scanner(bankFile);
        String fName = null, lName = null, dOB = null, addr = null, pNum = null, 
               checkAccNum = null,credAccNum = null, savAccNum = null, 
               idNum = null, checkBal = null, savBal = null, creBal = null;

        String nameDescription = bankFileSc.nextLine();
        for(int cnt = 0; cnt < aCustomer.length && bankFileSc.hasNext(); cnt++){
            aCustomer[cnt] = new Customer(fName, lName, dOB, idNum,
                                                     addr, pNum, checkAccNum,
                                                     credAccNum, savAccNum,
                                                     checkBal, savBal, creBal);
            aCustomer[cnt].Load(bankFileSc);
        
        }
         
        bankFileSc.close();

    }
     
    public static char GetUserChoice1(char userChoice1){
        
        /*Description: Gets a user's choice and returns the user's choice.*/

        Scanner kbd = new Scanner(System.in);

        System.out.println("\nType the letter for the desired choice.\n"
                + "TYPE A if you're an indiviual person\n"
                + "TYPE B if you're multple people\n"
                + "TYPE Q if you want to Quit");

        userChoice1 = kbd.nextLine().charAt(0);    
        return userChoice1;
    }
    
    public static char GetUserChoice2(char userChoice2){
        
        /*Description: Gets a user's second choice and returns the user's 
                       choice.*/

        Scanner kbd = new Scanner(System.in);

        System.out.println("\nAre you a Bank Manager?\n"
                + "TYPE Y for Yes\n"
                + "TYPE N for No\n");

        userChoice2 = kbd.nextLine().charAt(0);    
        return userChoice2;
    }
    
    public static char GetUserChoice3(char userChoice3){
        
        /*Description: Gets a user's third choice and returns the user's 
                       choice.*/

        Scanner kbd = new Scanner(System.in);

        System.out.println("\nWhat would you like to do?\n"
                + "TYPE I to inquire a balance\n"
                + "TYPE D to deposit money \n"
                + "TYPE W to withdraw money\n"
                + "TYPE T to transfer money\n");

        userChoice3 = kbd.nextLine().charAt(0);    
        return userChoice3;
    }    
    
    public static void DecideIfThePersonIsAManager(char userChoice2,
                                                   Customer[] aCustomer,
                                                   int numOfCustomers,
                                                   FileWriter fw, int size)
                                                   throws IOException{
    
        char userChoice3 = 'Z';
        
        switch(userChoice2){
        
            case 'Y':
            case 'y':
                PerformManagersChoice(numOfCustomers, aCustomer);
                break;
            
            case 'N':
            case 'n':
                userChoice3 = GetUserChoice3(userChoice3);
                PerformIndividualPersonChoice(userChoice3, aCustomer, 
                                              numOfCustomers, fw,size);
                break;
                
            default:
                System.out.println("Not a choice for an individual");
            
                
        }
        
    }

    public static void PerformIndividualPersonChoice(char userChoice3, 
                                              Customer[] aCustomer, 
                                              int numOfCustomers,
                                              FileWriter fw,int size)
                                              throws IOException{
        
    
        switch (userChoice3) {

            case 'I':
            case 'i':
                GetAccountTypeInquire(aCustomer, numOfCustomers,fw, size);
                break;

            case 'D':
            case 'd':
                GetAccountTypeDeposit(aCustomer, numOfCustomers,fw);
                break;

            case 'W':
            case 'w':
                GetAccountTypeWithdrawal(aCustomer, numOfCustomers,fw);
                break;
                
            case 'T':
            case 't':
                GetAccountTypeTransfer(aCustomer, numOfCustomers,fw);
                break;
         
            default:
                System.out.println("Not a choice try again");       
        }
        
    }
    
    
    public static void GetAccountTypeInquire(Customer [] aCustomer, 
                                            int numOfCustomers,
                                            FileWriter fw,int size)
                                            throws IOException{
        
        /* Description: The user is asked for the account type. After the 
                        account type is typed in it is then returned.*/
        
        String accType;
        Checking cH;
        cH = new Checking();
        Savings s;
        s = new Savings();
        Credit cR;
        cR = new Credit();
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the account type?\n"
                         + "TYPE 1 - Checking\n"
                         + "TYPE 2 - Credit\n"
                         + "TYPE 3 - Savings\n");
        
        accType = kbd.nextLine();        
    
        
        switch(accType){
            case "1":
                cH.InquireBalance(aCustomer,size, numOfCustomers,fw);
                break;
                
            case "2":
                cR.InquireBalance(aCustomer, size, numOfCustomers,fw);
                break;
            case "3":
                s.InquireBalance(aCustomer, size, numOfCustomers,fw);
                break;
            default:
                System.out.println("Not a choice");
        }
    }
    public static void GetAccountTypeDeposit(Customer [] aCustomer, 
                                             int numOfCustomers,
                                             FileWriter fw)throws IOException{
        
        /* Description: The user is asked for the account type. After the 
                        account type is typed in it is then returned.*/
        
        String accType;
        Checking cH;
        cH = new Checking();
        Savings s;
        s = new Savings();
        Credit cR;
        cR = new Credit();
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the account type?\n"
                         + "TYPE 1 - Checking\n"
                         + "TYPE 2 - Credit\n"
                         + "TYPE 3 - Savings\n");
        
        accType = kbd.nextLine();        
    
        
        switch(accType){
            case "1":
                cH.DepositMoney(aCustomer, numOfCustomers, fw);
                break;
                
            case "2":
                cR.DepositMoney(aCustomer, numOfCustomers, fw);
                break;
                
            case "3":
                s.DepositMoney(aCustomer, numOfCustomers, fw);
                break;
            default:
                System.out.println("Not a choice");
        }
    }
    
    public static void GetAccountTypeWithdrawal(Customer [] aCustomer, 
                                             int numOfCustomers,
                                             FileWriter fw)throws IOException{
        
        /* Description: The user is asked for the account type. After the 
                        account type is typed in it is then returned.*/
        
        String accType;
        Checking cH;
        cH = new Checking();
        Savings s;
        s = new Savings();
        Credit cR;
        cR = new Credit();
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the account type?\n"
                         + "TYPE 1 - Checking\n"
                         + "TYPE 2 - Credit\n"
                         + "TYPE 3 - Savings\n");
        
        accType = kbd.nextLine();        
    
        
        switch(accType){
            case "1":
                cH.WithdrawMoney(aCustomer, numOfCustomers, fw);
                break;
                
            case "2":
                cR.WithdrawMoney(aCustomer, numOfCustomers, fw);
                break;
                
            case "3":
                s.WithdrawMoney(aCustomer, numOfCustomers, fw);
                break;
            default:
                System.out.println("Not a choice");
        }
    }
    
    public static void GetAccountTypeTransfer(Customer [] aCustomer, 
                                             int numOfCustomers,
                                             FileWriter fw)throws IOException{
        
        /* Description: The user is asked for the account type. After the 
                        account type is typed in it is then returned.*/
        
        String accSource;
        Checking cH;
        cH = new Checking();
        Savings s;
        s = new Savings();
        Credit cR;
        cR = new Credit();
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What acount are transferring from?\n"
                         + "TYPE 1 - Checking\n"
                         + "TYPE 2 - Credit\n"
                         + "TYPE 3 - Savings\n");
        
        accSource = kbd.nextLine();        
    
        
        switch(accSource){
            case "1":
                cH.TransferMoney(aCustomer, numOfCustomers, fw);
                break;
                
            case "2":
                cR.TransferMoney(aCustomer, numOfCustomers, fw);
                break;
                
            case "3":
                s.TransferMoney(aCustomer, numOfCustomers, fw);
                break;
            default:
                System.out.println("Not a choice");
        }
    }

        public static void PerformManagersChoice(int numOfCustomers, Customer [] aCustomer){
            
            int managersChoice;
            Scanner kbd = new Scanner(System.in);
            
            System.out.println("How do you want to inquire the data?\n"
                               + "TYPE 1 - Inquire account by name\n"
                               + "TYPE 2 - Inquire account by type/number\n");
            
            
            managersChoice = kbd.nextInt();
            
            switch(managersChoice){
                case 1:
                    InquireByName(numOfCustomers, aCustomer);
                    break;
                
                case 2:
                    InquireByTypeAndNumber(numOfCustomers, aCustomer);
                    break;
                    
                default:    System.out.println("Not a choice of the manager's");    
            
            }
        
        }    
        
        public static void InquireByName(int numOfCustomers, 
                                         Customer [] aCustomer){
            String userAccName;
            int loc = 0;
            Scanner kbd = new Scanner(System.in);
            
            System.out.println("Who's account would you like to inquire about?");
            userAccName = kbd.nextLine();
            
            while(loc < numOfCustomers && !userAccName.equals((aCustomer[loc].firstName)+" "+aCustomer[loc].lastName)){
                loc++;
            }
            if(userAccName.equals((aCustomer[loc].firstName)+" "+aCustomer[loc].lastName)){
                System.out.println("Date of Birth: " + aCustomer[loc].dateOfBirth);
                System.out.println("Address: " + aCustomer[loc].GetAddress());
                System.out.println("Phone Number: " + aCustomer[loc].phoneNumber);
                System.out.println("Identification Number: " + aCustomer[loc].GetId());
                System.out.println("Checking Account Number: " + aCustomer[loc].GetCheckingAccountNumber());
                System.out.println("Savings Account Number:" + aCustomer[loc].GetSavingsAccountNumber());
                System.out.println("Creidt Account Number:" + aCustomer[loc].GetCreditAccountNumber());
                System.out.println("Checking Account Balance:" + aCustomer[loc].GetCheckingBalance());
                System.out.println("Savings Account Balance:" + aCustomer[loc].GetSavingsBalance());
                System.out.println("Credit Account Balance:" + aCustomer[loc].GetCreditBalance());
            }
            else
                System.out.println("Name couldn't be found");
        }
        
        public static void InquireByTypeAndNumber(int numOfCustomers, Customer [] aCustomer){
            
            int accType, accNum, loc = 0;
            Scanner kbd = new Scanner(System.in);
        
            System.out.println("What is the account type?\n"
                         + "TYPE 1 - Checking\n"
                         + "TYPE 2 - Credit\n"
                         + "TYPE 3 - Savings\n");

            accType = kbd.nextInt();
            
            System.out.println("What is the account number?\n");
            accNum = kbd.nextInt();
            
            switch(accType){
                case 1:
                    while (loc < numOfCustomers && accNum != Integer.parseInt(aCustomer[loc].GetCheckingAccountNumber())) {
                        loc++;
                    }
                    if (accNum == Integer.parseInt(aCustomer[loc].GetCheckingAccountNumber())) {
                        System.out.println("Name: "+aCustomer[loc].firstName+" "+aCustomer[loc].lastName);
                        System.out.println("Date of Birth: " + aCustomer[loc].dateOfBirth);
                        System.out.println("Address: " + aCustomer[loc].GetAddress());
                        System.out.println("Phone Number: " + aCustomer[loc].phoneNumber);
                        System.out.println("Identification Number: " + aCustomer[loc].GetId());
                        System.out.println("Checking Account Number: " + aCustomer[loc].GetCheckingAccountNumber());
                        System.out.println("Savings Account Number:" + aCustomer[loc].GetSavingsAccountNumber());
                        System.out.println("Creidt Account Number:" + aCustomer[loc].GetCreditAccountNumber());
                        System.out.println("Checking Account Balance:" + aCustomer[loc].GetCheckingBalance());
                        System.out.println("Savings Account Balance:" + aCustomer[loc].GetSavingsBalance());
                        System.out.println("Credit Account Balance:" + aCustomer[loc].GetCreditBalance());
                    } else {
                        System.out.println("Name couldn't be found");
                    }
                    break;
                case 2:
                    while (loc < numOfCustomers && accNum != Integer.parseInt(aCustomer[loc].GetCreditAccountNumber())) {
                        System.out.println(aCustomer[loc].GetCreditAccountNumber());
                        loc++;
                    }
                    if (accNum == Integer.parseInt(aCustomer[loc].GetCreditAccountNumber())) {
                        System.out.println("Name: "+aCustomer[loc].firstName+" "+aCustomer[loc].lastName);
                        System.out.println("Date of Birth: " + aCustomer[loc].dateOfBirth);
                        System.out.println("Address: " + aCustomer[loc].GetAddress());
                        System.out.println("Phone Number: " + aCustomer[loc].phoneNumber);
                        System.out.println("Identification Number: " + aCustomer[loc].GetId());
                        System.out.println("Checking Account Number: " + aCustomer[loc].GetCheckingAccountNumber());
                        System.out.println("Savings Account Number:" + aCustomer[loc].GetSavingsAccountNumber());
                        System.out.println("Creidt Account Number:" + aCustomer[loc].GetCreditAccountNumber());
                        System.out.println("Checking Account Balance:" + aCustomer[loc].GetCheckingBalance());
                        System.out.println("Savings Account Balance:" + aCustomer[loc].GetSavingsBalance());
                        System.out.println("Credit Account Balance:" + aCustomer[loc].GetCreditBalance());
                    } else {
                        System.out.println("Name couldn't be found");
                    }                    
                    break;
                case 3:
                    while (loc < numOfCustomers && accNum != Integer.parseInt(aCustomer[loc].GetSavingsAccountNumber())) {
                        loc++;
                    }
                    if (accNum == Integer.parseInt(aCustomer[loc].GetSavingsAccountNumber())) {
                        System.out.println("Name: "+aCustomer[loc].firstName+" "+aCustomer[loc].lastName);
                        System.out.println("Date of Birth: " + aCustomer[loc].dateOfBirth);
                        System.out.println("Address: " + aCustomer[loc].GetAddress());
                        System.out.println("Phone Number: " + aCustomer[loc].phoneNumber);
                        System.out.println("Identification Number: " + aCustomer[loc].GetId());
                        System.out.println("Checking Account Number: " + aCustomer[loc].GetCheckingAccountNumber());
                        System.out.println("Savings Account Number:" + aCustomer[loc].GetSavingsAccountNumber());
                        System.out.println("Creidt Account Number:" + aCustomer[loc].GetCreditAccountNumber());
                        System.out.println("Checking Account Balance:" + aCustomer[loc].GetCheckingBalance());
                        System.out.println("Savings Account Balance:" + aCustomer[loc].GetSavingsBalance());
                        System.out.println("Credit Account Balance:" + aCustomer[loc].GetCreditBalance());
                    } else {
                        System.out.println("Name couldn't be found");
                    }  
                    break;
          
            }
        }

        public static void PerformMultiplePeopleChoice(Customer [] aCustomer, 
                                                       int numOfCustomers,
                                                       FileWriter fw)
                                                       throws IOException{
        /*Description: In this method one person is able to pay another person 
                       with the same bank. First the payer's account number is 
                       asked. Then the person getting paid account number is 
                       asked. After  both the payer's account number and payee's
                       account number is returned to this method where both are 
                       used along with loc1(positon of payer's account 
                       information and loc2(position of payee's account 
                       information). 
         */
        
        int payerAccNum, payeeAccNum, loc1, loc2, payerAccType;
        double payerBalance, payeeBalance;


        String accSource;
        Checking cH;
        cH = new Checking();
        Savings s;
        s = new Savings();
        Credit cR;
        cR = new Credit();
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("Paying someone is the only option under multiple people\n");
        
        System.out.print("What type of acount is the payer paying from?\n"
                         + "TYPE 1 - Checking\n"
                         + "TYPE 2 - Credit\n"
                         + "TYPE 3 - Savings\n");
        
        payerAccType = kbd.nextInt();
        
        switch(payerAccType){
            case 1:
                cH.PaySomeone(aCustomer, numOfCustomers, fw);
                break;
                
            case 2:
                cR.PaySomeone(aCustomer, numOfCustomers, fw);
                break;
                
            case 3:
                s.PaySomeone(aCustomer, numOfCustomers, fw);
                break;
            default:
                System.out.println("Not a choice");
        }
        

        }
}