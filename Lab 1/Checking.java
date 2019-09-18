package checking;

import java.util.*;
import java.io.*;

public class Checking {
    
    /*  Programmer: Solomon Davis
        Alias: Spiderman
        Due Date: Thursday, September 12, 2019
        Course: Advanced Object Oriented Programming (CS 3331)
        Instructor: Daniel Meija
        Lab Assignment: Programming Assignment 1
        Lab Description: In this lab you are organizing a bank that must allow
                         customers to inquire a balance, pay someone, deposit
                         money, withdraw money, and log all transactions. Logged
                         tranactions will be written to a text file
        Honesty Statement: I can confirm that the work of this assignment is 
                           completely my own. By turning in this assignment, I
                           declare that I not recieve unauthorized assistance.
                           Moreover, all deliverables including, but not limited
                           to the source code, lab report and outpuut files were
                           written and produced by me alone.

     */    
    

    public static void main(String[] args) throws IOException {
        

        int numEntries = 0;
        char userChoice = 'J';
        CustomerInfo[] customer;
        customer = new CustomerInfo[1000];
        PrintWriter pw;
        pw = new PrintWriter("Logs.txt");
        FileWriter fw;
        File logFile = new File("Logs.txt");
        fw = new FileWriter(logFile,true);
        fw.write("First Name        " + "Last Name      "+ "Account#        "
                 +"Type Of Transaction      " + "Comment\n");

        
        numEntries = ReadFile(customer);

            
        while(userChoice != 'q' && userChoice != 'Q'){
            
            userChoice = GetUserChoice(userChoice);
            if (userChoice != 'q' && userChoice !='Q'){
                PerformUserChoice(userChoice,numEntries,customer,fw);            
            
            }
        
        }
        
        fw.close();
    }

    public static int ReadFile(CustomerInfo[] customer) throws IOException {

        /*Description: Reads the bank user file, counts the number of entries in
                       the file, and returns the number of entries is returned.
        */
        
        int numEntries;
        numEntries = 0;
        File bankFile = new File("Bank Users.txt");
        Scanner bankSc = new Scanner(bankFile);
        String nameDescription = bankSc.nextLine();

        while (numEntries < customer.length && bankSc.hasNext()) {
            customer[numEntries] = new CustomerInfo();
            customer[numEntries].Load(bankSc);
            numEntries++;
        }
 
        bankSc.close();
        return numEntries;
        
    }

    public static char GetUserChoice(char userChoice) {
        /*Description: Gets a user's choice and returns the user's choice.*/

        Scanner kbd = new Scanner(System.in);

        System.out.println("\nMAKE SURE TO SAVE BEFORE YOU QUIT\n"
                + "LOGS ARE AUTOMATICALLY ADDED TO A FILE ONCE A TRANSACTION"
                + " IS COMPLETED\n"
                + "What do you want to do?\n"
                + "TYPE B to Get a balance\n"
                + "TYPE P to Pay someone\n"
                + "TYPE D to Deposit money\n"
                + "TYPE W to Withdraw money\n"
                + "TYPE Q to Quit\n"
                + "TYPE S to Save Changes\n");

        userChoice = kbd.nextLine().charAt(0);

        return userChoice;

    }

    public static void PerformUserChoice(char userChoice, int numEntries,
                                        CustomerInfo [] customer, FileWriter fw)
                                        throws IOException{
        
        /*Descripton: Performs the user's choice or choices. */

        switch (userChoice) {

            case 'B':
            case 'b':
                BalanceInquiry(customer, numEntries);
                break;

            case 'P':
            case 'p':
                PaySomeone(customer, numEntries,fw);
                break;

            case 'D':
            case 'd':
                DepositMoney(customer, numEntries,fw);
                break;

            case 'W':
            case 'w':
                WithdrawMoney(customer, numEntries,fw);
                break;
                
            case 'S':
            case 's':
                Save(customer,numEntries);
                break;
                
            default:
                System.out.println("Not a choice try again");   
                
                       
            
        }
        
  

    }

    public static void BalanceInquiry(CustomerInfo [] customer, int numEntries)
                                     throws IOException{
        
        /*Description: This method holds all the methods for getting a balance 
                       of an account. The user is asked for the account number 
                       in the first method. Then the account is retrieved based 
                       off the account number. After the account
                       balanced is displayed to the user.
        */
        
        int accNum;
        int loc;
        accNum = GetAccountNumber();
        loc = RetrieveAccountBalance(accNum,customer,numEntries);
        DisplayBalance(customer, numEntries,loc);
           
        
    }
    
    public static int GetAccountNumber(){
        
        /* Description: The user is asked for the account number. After the 
                        account number is typed in it is then returned.*/
        
        int accNum;
        
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the account # of the person you wish to "
                         + "obtain a balance from\n");
        
        accNum = kbd.nextInt();        
    
        return accNum;
    
    }
    
    public static int RetrieveAccountBalance(int accNum, CustomerInfo [] 
                                              customer, int numEntries)
                                              throws IOException {
        
        /* Description: The variables loc and accName are used to locate the
                        correct locating in the customer info  array of onjects.
                        The variable loc acts as the array position while the
                        variable numEntries is of number of entries or the 
                        number of people with accounts. This is why the while 
                        loop continues as long as loc is less than numEntries
                        and the account number typed by the user matches an
                        account number in the array of objects. If the correct
                        location isn't found the variable loc is equal to 
                        numEntries which means the correct info isn't found.
                        After the loop is finished the variable loc is returned.
        */
        
        
        
        int loc;
        loc = 0;
        
        while(loc < numEntries && accNum != customer[loc].GetAccountNumber()){
        
            loc++;
        }
        
        return loc;
    }
    
    public static void DisplayBalance(CustomerInfo [] customer, int numEntries, 
                                    int loc){
        /*Description: If the loc variable that is passed through is less than
                       numEntries it means the correct customer info was found
                       and confirmation messages will be displayed to the user. 
                       If the correction location wasn't founded a message will 
                       be displayed saying the member wasn't found */
        
        if (loc < numEntries) {
            
            System.out.printf("\nThe account of " + customer[loc].GetFirstName()
                              + " "  +customer[loc].GetLastName() +
                              " was accessed\n");            
            System.out.printf("The Balance is $" + customer[loc].GetBalance());
            
        } else {
            System.out.println("\nMember not found\n");
        }

    }

    public static void PaySomeone(CustomerInfo [] customer, int numEntries,
                                 FileWriter fw) throws IOException  {
        
        /*Description: In this method one person is able to pay another person 
                       with the same bank. First the payer's account number is 
                       asked. Then the person getting paid account number is 
                       asked. After  both the payer's account number and payee's
                       account number is returned to this method where both are 
                       used along with loc1(positon of payer's account 
                       information and loc2(position of payee's account 
                       information). 
         */
        
        int payerAccNum, payeeAccNum, loc1, loc2;
        double payerBalance, payeeBalance;

        payerAccNum = GetPayer();
        payeeAccNum = GetPayee();
        loc1 = payerRetrieval(payerAccNum,customer,numEntries);
        loc2 = payeeRetrieval(payeeAccNum,customer,numEntries);
        AmountPaid(payerAccNum,payeeAccNum, loc1, loc2, numEntries,
                                customer,fw);
        
        
    }
    
    public static int GetPayer(){
        
        //Description: Get payer's account number from the user

        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the account # of the payer?\n");       
    
        int payerAccNum = kbd.nextInt();
        
        return payerAccNum;
    }
    
    public static int GetPayee(){
        
        //Description: Get payee's account number from the user
    
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the account # of the person getting paid?\n");
        
        int payeeAccNum = kbd.nextInt();
                
        return payeeAccNum;
    }
    
    public static int payerRetrieval(int payerAccNum,CustomerInfo [] customer,
                                     int numEntries){
        
        //Description: Retrieves payer's account information.
        
        int loc1;
        loc1 = 0;
    
        while(loc1 < numEntries && 
              payerAccNum != customer[loc1].GetAccountNumber()){
        
            loc1++;
        }        
        
        
        return loc1;
    }
    
    public static int payeeRetrieval(int payerAccNum,CustomerInfo [] customer,
                                     int numEntries){
        
        //Description: Retrieves payee's account information.
        
        int loc2;
        loc2 = 0;
    
        while(loc2 < numEntries && 
              payerAccNum != customer[loc2].GetAccountNumber()){
        
            loc2++;
        }        
        
        
        return loc2;
    }    
    
    
    public static void AmountPaid(int payerAccNum, int payeeAccNum, int loc1, 
                                  int loc2,int numEntries, CustomerInfo[]
                                  customer, FileWriter fw) throws IOException{
        
        /*Description: Takes payee's and payer's account information and uses it
                       to transfer funds from the payer's account into the 
                       payer's account.
        */
        
        double amountPaid;
        double newBalancePayer;
        
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the amount being paid?");
        
        amountPaid = kbd.nextDouble();

        if (loc1 < numEntries){
            if (customer[loc1].GetBalance() >= amountPaid){
                newBalancePayer = customer[loc1].GetBalance() 
                                         - amountPaid;
                customer[loc1].Set(customer[loc1].GetFirstName(),
                                   customer[loc1].GetLastName(), 
                                   customer[loc1].GetAccountNumber(), 
                                   customer[loc1].GetCheckingAccount(),
                                   customer[loc1].GetSavingsAccount(),
                                   newBalancePayer,
                                   customer[loc1].GetInterstRate());
        
                if (loc2 < numEntries){
                    double newBalancePayee = customer[loc2].GetBalance() + 
                                             amountPaid;
                    customer[loc2].Set(customer[loc2].GetFirstName(),
                                       customer[loc2].GetLastName(), 
                                       customer[loc2].GetAccountNumber(), 
                                       customer[loc2].GetCheckingAccount(),
                                       customer[loc2].GetSavingsAccount(),
                                       newBalancePayee,
                                       customer[loc2].GetInterstRate());
                    fw.write(customer[loc2].GetFirstName()+ "              "
                             +customer[loc2].GetLastName()+"           "
                             +customer[loc2].GetAccountNumber()+"       "
                             +"Paying Someone           "
                             +"Paid $" + amountPaid + " by " 
                             +customer[loc1].GetFirstName() + " " 
                             +customer[loc1].GetLastName()+ "\n" );
                    
            
                }
                System.out.println("The Transaction was completed");
            }
            else {
                System.out.println("The amount must be less than or equal to "
                                   + "the amount in the payer's account ");
            }
        }
        else {
        
            System.out.println("Tranasaction could not be completed");
        }
        
    }

    public static void DepositMoney(CustomerInfo [] customer, int numEntries, 
                                   FileWriter fw) throws IOException {
        
        /*Description: Deposits money into the account number the user types in.
        */
        
        int accNum, loc; 
        double depositAmount;
        
        accNum = GetAccountNumberDeposit();
        depositAmount = GetDepositAmount();
        loc = RetrieveBalanceDeposit(accNum, depositAmount, customer, 
                                     numEntries);
        Deposit(customer,numEntries,loc,depositAmount,fw);
        
    }
    
    public static int GetAccountNumberDeposit(){
        
        /*Description: Asks the user for the account number and returns the 
                       account number.
        */
    
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the account # of the account the money is"
                           + " being deposited to?");
        
        int accNum = kbd.nextInt();
        
        return accNum;
    
    }
    
    public static double GetDepositAmount(){
        
        /*Description: Asks the user for the deposit amount and returns the 
                       deposit amount.
        */        
    
        double depositAmount;
        
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the amount money being deposited?");
        
        depositAmount = kbd.nextDouble();
        
        if (depositAmount < 0){
            
            System.out.println("The value entered was negative try again");
            GetDepositAmount();
        
        }
        
        return depositAmount;    
        
    }

    public static int RetrieveBalanceDeposit(int accNum, double depositAmount, 
                                             CustomerInfo [] customer,
                                             int numEntries){
        
        /*Description: Retrieves the balance of an account using the account 
                       number the user types in and the location of the 
                       information in the array of objects.*/
        
        int loc;
        loc = 0;
        
        while(loc < numEntries && accNum != customer[loc].GetAccountNumber()){
        
            loc++;
        }               
        
        
        return loc;
    
    
    }

    public static void Deposit(CustomerInfo [] customer, int numEntries, 
                              int loc, double depositAmount, FileWriter fw) 
                              throws IOException{
        
        /*Description: Deposits the deposit amount into the acoount the user
                       choose.*/
        
        double newBalance;
        
        if (loc < numEntries) {
            
            newBalance = customer[loc].GetBalance() + depositAmount;
            System.out.printf("\nThe account of " + customer[loc].GetFirstName()
                              + " "  +customer[loc].GetLastName() +
                              " was accessed\n");
            System.out.print("Old Balance $"+ customer[loc].GetBalance()+ "\n");
            customer[loc].Set(customer[loc].GetFirstName(), 
                              customer[loc].GetLastName(),
                              customer[loc].GetAccountNumber(), 
                              customer[loc].GetCheckingAccount(), 
                              customer[loc].GetSavingsAccount(), 
                              newBalance, 
                              customer[loc].GetInterstRate());
            fw.write(customer[loc].GetFirstName()+ "              "
                     +customer[loc].GetLastName()+"           "
                     +customer[loc].GetAccountNumber()+"       "
                     +"Deposit           "
                     +"Deposited $" + depositAmount + " into account \n" );
            System.out.print("New Balance $"+ customer[loc].GetBalance()+ "\n");
            System.out.print("Deposit Complete\n");
            
        
        } else {
            System.out.println("\nMember not found\n");
        }    
    
        
    }

    public static void WithdrawMoney(CustomerInfo [] customer, int numEntries, 
                                    FileWriter fw) throws IOException {
        
        /*Description: Withdraws money into the account number the user types 
                       in.
        */        
        
        int accNum, loc; 
        double withdrawalAmount;
        
        accNum = GetAccountNumberWithdrawal();
        withdrawalAmount = GetWithdrawalAmount();
        loc = RetrieveBalanceWithdrawn(accNum, withdrawalAmount, customer, 
                                     numEntries);
        Withdrawal(customer,numEntries,loc,withdrawalAmount,fw);

        
    }
    
    public static int GetAccountNumberWithdrawal(){
        
        /*Description: Asks the user for the account and returns the 
                       account number.
        */             
    
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the account # of the account the money is"
                           + " being withdrawn from?");
        
        int accNum = kbd.nextInt();
        
        return accNum;        
    
    }
    
    public static double GetWithdrawalAmount(){
        
        /*Description: Asks the user for the deposit amount and returns the 
                       deposit amount.
        */
        
        double withdrawalAmount;
    
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the amount money being withdrawn?");
        
        withdrawalAmount = kbd.nextDouble();
        
        if (withdrawalAmount < 0){
            
            System.out.println("The value entered was negative try again");
            GetWithdrawalAmount();
        
        }        
        
        return withdrawalAmount;            
    
    }
    
    public static int RetrieveBalanceWithdrawn(int accNum, double depositAmount, 
                                               CustomerInfo [] customer,
                                               int numEntries){
        
        /*Description: Retrieves the balance of an account using the account 
                       number the user types in and the location of the 
                       information in the array of objects.*/        
        
        int loc;
        loc = 0;
        
        while(loc < numEntries && accNum != customer[loc].GetAccountNumber()){
        
            loc++;
        }               
        
        
        return loc;
    
    
    }   

    public static void Withdrawal(CustomerInfo [] customer, int numEntries, 
                                 int loc, double withdrawnAmoumt, FileWriter fw) 
                                 throws IOException{
        
        /*Description: Withdraws the withdrawal amount into the acoount the user
                       choose.*/
        
        double newBalance;
        
        if (loc < numEntries) {
            
            newBalance = customer[loc].GetBalance() - withdrawnAmoumt;
            
            System.out.printf("\nThe account of " + customer[loc].GetFirstName()
                              + " "  +customer[loc].GetLastName() +
                              " was accessed\n");
            System.out.print("Old Balance $"+ customer[loc].GetBalance() 
                             + "\n");
            customer[loc].Set(customer[loc].GetFirstName(), 
                              customer[loc].GetLastName(),
                              customer[loc].GetAccountNumber(), 
                              customer[loc].GetCheckingAccount(), 
                              customer[loc].GetSavingsAccount(), 
                              newBalance, 
                              customer[loc].GetInterstRate());
            fw.write(customer[loc].GetFirstName()+ "              "
                     +customer[loc].GetLastName()+"           "
                     +customer[loc].GetAccountNumber()+"       "
                     +"Withdrawal           "
                     +"Withdrew $" + withdrawnAmoumt + " from account\n" );
            System.out.print("New Balance $"+ customer[loc].GetBalance()+ "\n");
            System.out.print("Withdrawal Complete\n");
            
        } else {
            System.out.println("\nMember not found\n");
        }    
    
       
    }    
    
    
    public static void Save(CustomerInfo [] customer, int numEntries) 
                            throws IOException{
        
        /*Description: Write the most current information from the CustomerInfo
                       into the Bank user file.*/
        
        FileWriter fw;
        
        File bankFile = new File("Bank Users.txt");
        
        fw = new FileWriter(bankFile, false);
        
        fw.write("First Name        " + "Last Name      " + "Account Number    "
                 + " " + "Checking       " + "Savings            "
                 +"Checking Starting Balance    "+"Interest\n");
        
        for (int cnt = 0; cnt < numEntries; cnt++){
        
            fw.write(customer[cnt].GetFirstName() + "             "
                     +customer[cnt].GetLastName()+ "             "
                     +customer[cnt].GetAccountNumber()+ "               "
                     +customer[cnt].GetCheckingAccount()+ "             "
                     +customer[cnt].GetSavingsAccount()+ "              ");
            
            fw.write(customer[cnt].GetBalance() + "           ");
            fw.write(customer[cnt].GetInterstRate()+"\n");

        }
    

        fw.close();
        
        System.out.println("Information has been updated");
    
    }


}
