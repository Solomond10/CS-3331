package bank;

import java.util.Scanner;
import java.io.*;

public class Checking extends Account {
    

    @Override 
    public void InquireBalance(Customer [] aCustomer, int numOfCustomers,
                               int size, FileWriter fw) throws IOException{
        String accNum;
        int loc;
        
        accNum = GetAccountNumber();
        RetrieveAccountBalance(accNum,aCustomer,numOfCustomers,fw);

      
    }
    
    public static String GetAccountNumber(){
        
        /* Description: The user is asked for the account number. After the 
                        account number is typed in it is then returned.*/
        
        String accNum;
        
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the account # of the person you wish to "
                         + "obtain a balance from\n");
        
        accNum = kbd.nextLine();        
    
        return accNum;
    
    }
           
   public static int RetrieveAccountBalance(String accNum, Customer [] 
                                              aCustomer, int numOfCustomers,
                                              FileWriter fw)
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
        
        while(loc < numOfCustomers && !accNum.equals(
                                    aCustomer[loc].GetCheckingAccountNumber())){
            loc++;
        }
        
        if(accNum.equals(aCustomer[loc].GetCheckingAccountNumber())){
            System.out.printf("\nThe account of " 
                               + aCustomer[loc].GetFirstName()
                               +" "  +aCustomer[loc].GetLastName() 
                               +" was accessed\n");            
            System.out.printf("The checking balance is $" 
                    + aCustomer[loc].GetCheckingBalance());

            LogChecking log;
            log = new LogChecking();
            log.LogInquiry(accNum, aCustomer, numOfCustomers, fw, loc);
            
        }
        else{
        
            System.out.println("The balance couldn't be accessed."
                               + " Check the account number.");
        }
        return loc;
    }    

    @Override
    public void DepositMoney(Customer [] aCustomer, int numOfCustomers, 
                                   FileWriter fw) throws IOException {
        
        /*Description: Deposits money into the account number the user types in.
        */
        
        int accNum, loc; 
        double depositAmount;
        
        accNum = GetAccountNumberDeposit();
        depositAmount = GetDepositAmount();
        RetrieveBalanceDeposit(accNum, depositAmount, aCustomer, 
                               numOfCustomers,fw);
//        Save(aCustomer,numOfCustomers);
        //Deposit(aCustomer,numOfCustomers,loc,depositAmount,fw);
        
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

    public static void RetrieveBalanceDeposit(int accNum, double depositAmount, 
                                             Customer [] aCustomer,
                                             int numOfCustomers, FileWriter fw)
                                             throws IOException{
        
        /*Description: Retrieves the balance of an account using the account 
                       number the user types in and the location of the 
                       information in the array of objects.*/
        
        int loc;
        loc = 0;
        
        while(loc < numOfCustomers && accNum != Double.parseDouble(
                                    aCustomer[loc].GetCheckingAccountNumber())){
        
            loc++;
        }               
        
        double newBalance;

        if (accNum == Double.parseDouble(
                                aCustomer[loc].GetCheckingAccountNumber())) {

            newBalance = Double.parseDouble(aCustomer[loc].GetCheckingBalance()) 
                         + depositAmount;
            System.out.printf("\nThe account of " 
                    + aCustomer[loc].GetFirstName()
                    + " " + aCustomer[loc].GetLastName()
                    + " was accessed\n");
            System.out.print("Old Balance $" 
                    + aCustomer[loc].GetCheckingBalance()
                    + "\n");
            aCustomer[loc] = new Customer(aCustomer[loc].firstName,
                    aCustomer[loc].lastName,
                    aCustomer[loc].dateOfBirth,
                    aCustomer[loc].GetId(),
                    aCustomer[loc].address,
                    aCustomer[loc].phoneNumber,
                    aCustomer[loc].GetCheckingAccountNumber(),
                    aCustomer[loc].GetSavingsAccountNumber(),
                    aCustomer[loc].GetCreditAccountNumber(),
                    newBalance + "",
                    aCustomer[loc].GetSavingsBalance(),
                    aCustomer[loc].GetCreditBalance());
            
            LogChecking log;
            log = new LogChecking();
            log.LogDeposit(aCustomer, numOfCustomers, loc, depositAmount, fw, newBalance);


            System.out.print("New Balance $" + newBalance + "\n");
            System.out.print("Deposit Complete\n");
            Save(aCustomer,numOfCustomers);
            
            

        } else {
            System.out.println("\nMember not found\n");
        }
    }
    
@Override
    public void WithdrawMoney(Customer [] aCustomer, int numOfCustomers, 
                                   FileWriter fw) throws IOException {
        
        /*Description: Deposits money into the account number the user types in.
        */
        
        int accNum, loc; 
        double withdrawAmount;
        
        accNum = GetAccountNumberWithdrawal();
        withdrawAmount = GetWithdrawalAmount();
        RetrieveBalanceWithdrawal(accNum, withdrawAmount, aCustomer, 
                               numOfCustomers,fw);

        
    }
    
    public static int GetAccountNumberWithdrawal(){
        
        /*Description: Asks the user for the account number and returns the 
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
            GetDepositAmount();
        
        }
        
        return withdrawalAmount;    
        
    }

    public static void RetrieveBalanceWithdrawal(int accNum, 
                                                 double withdrawalAmount, 
                                                 Customer [] aCustomer,
                                                 int numOfCustomers, 
                                                 FileWriter fw)
                                                 throws IOException{
        
        /*Description: Retrieves the balance of an account using the account 
                       number the user types in and the location of the 
                       information in the array of objects.*/
        
        int loc;
        loc = 0;
        
        while(loc < numOfCustomers && accNum != Double.parseDouble(
                                    aCustomer[loc].GetCheckingAccountNumber())){
        
            loc++;
        }               
        
        double newBalance;

        if (accNum == Double.parseDouble(
                                    aCustomer[loc].GetCheckingAccountNumber())) {

            newBalance = Double.parseDouble(aCustomer[loc].GetCheckingBalance()) 
                                                            - withdrawalAmount;
            System.out.printf("\nThe account of " 
                    + aCustomer[loc].GetFirstName()
                    + " " + aCustomer[loc].GetLastName()
                    + " was accessed\n");
            System.out.print("Old Balance $" 
                    + aCustomer[loc].GetCheckingBalance()
                    + "\n");
            aCustomer[loc] = new Customer(aCustomer[loc].firstName,
                    aCustomer[loc].lastName,
                    aCustomer[loc].dateOfBirth,
                    aCustomer[loc].GetId(),
                    aCustomer[loc].address,
                    aCustomer[loc].phoneNumber,
                    aCustomer[loc].GetCheckingAccountNumber(),
                    aCustomer[loc].GetSavingsAccountNumber(),
                    aCustomer[loc].GetCreditAccountNumber(),
                    newBalance + "",
                    aCustomer[loc].GetSavingsBalance(),
                    aCustomer[loc].GetCreditBalance());

            LogChecking log;
            log = new LogChecking();
            log.LogWithdrawal(fw, aCustomer, loc, withdrawalAmount, accNum, newBalance);
            Save(aCustomer,numOfCustomers);
            
            

        } else {
            System.out.println("\nMember not found\n");
        }
    }

    @Override
    public void TransferMoney(Customer [] aCustomer, int numOfCustomers, 
                                     FileWriter fw) throws IOException{
        
        String accSourceNum,accDestNum, loc;
        int accDest;
        double transferAmount;
        

        accSourceNum = GetAccountNumberOfTransferSource();
        accDest =  GetTransferDestination();
        accDestNum = GetAccountNumberOfTranserDestination();
        transferAmount = GetTransferAmount();
        ProcessTransfer(accSourceNum, accDestNum, accDest, transferAmount, aCustomer,
                        numOfCustomers, fw);
    }   
    
    public static String GetAccountNumberOfTransferSource(){
        
        String accSourceNum;
        
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the number of the account where you are "
                           + "getting the money from\n");
        
        accSourceNum = kbd.nextLine();    
        
        
        return accSourceNum;
    }
    
    public static int GetTransferDestination(){
        
        int accDest;
        
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What acount are transferring to?\n"
                         + "TYPE 1 - Checking\n"
                         + "TYPE 2 - Credit\n"
                         + "TYPE 3 - Savings\n");
        
        accDest = kbd.nextInt();        
        
        
        return accDest;
    }

    public static String GetAccountNumberOfTranserDestination(){
 
        String accDestNum;
        
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the number of the account where you are "
                           + "transfering the money to\n");
        
        accDestNum = kbd.nextLine();    
        
        
        return accDestNum;        
    
    }
    
    public static double GetTransferAmount(){
        
        /*Description: Asks the user for the deposit amount and returns the 
                       deposit amount.
        */        
    
        double transferAmount;
        
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the amount money being transfered?");
        
        transferAmount = kbd.nextDouble();
        
        if (transferAmount < 0){
            
            System.out.println("The value entered was negative try again");
            GetDepositAmount();
        
        }
        
        return transferAmount;    
        
    }    
    
    
    public static void ProcessTransfer(String accSourceNum, String accDestNum,
                                       int accDest, 
                                       double transferAmount, 
                                       Customer [] aCustomer,
                                       int numOfCustomers, 
                                       FileWriter fw)
                                       throws IOException{
        
        int loc = 0, loc2 = 0;
        double newBalance1, newBalance2;

        if (accSourceNum.charAt(3) == accDestNum.charAt(3)) {

            while (loc < numOfCustomers && accSourceNum.equals(aCustomer[loc].GetCheckingAccountNumber())) {
                loc++;
            }
            switch (accDest) {
                case 1:
                    System.out.println("You can transfer to and from the same account");
                    break;
                    

                case 2:
                    TransferToCredit(aCustomer, numOfCustomers, loc,
                            accDestNum, transferAmount,
                            accSourceNum, fw, accDest);
                    break;


                case 3:
                    TransferToSavings(aCustomer, numOfCustomers, loc,
                            accDestNum, transferAmount,
                            accSourceNum, fw, accDest);
                    break;

                default:
                    System.out.println("That's not an account type");
            }
        }
        else
            System.out.println("Accounts are different customers");
    }

    public static void TransferToCredit(Customer [] aCustomer,
                                            int numOfCustomers, int loc,
                                            String accDestNum,
                                            double transferAmount, 
                                            String accSourceNum,
                                            FileWriter fw,int accDest)
                                            throws IOException{
        
        double newBalance1 = 0, newBalance2 = 0;
        int loc2 = 0;

        while (loc2 < numOfCustomers && accDestNum.equals(aCustomer[loc2].GetCreditAccountNumber())) {
            loc2++;
        }

        if(Double.parseDouble(aCustomer[loc].GetCheckingBalance()) == 0) {
            System.out.print("You don't have any money in your checking account");
        
        }else if (accSourceNum.equals(
                            aCustomer[loc].GetCheckingAccountNumber())) {

            newBalance1 = Double.parseDouble(aCustomer[loc].GetCheckingBalance()) 
                                            - transferAmount;
            
            newBalance2 = Double.parseDouble(aCustomer[loc].GetCreditBalance()) 
                                            + transferAmount;
            
            System.out.printf("\nThe account of " 
                              + aCustomer[loc].GetFirstName()
                    + " " + aCustomer[loc].GetLastName()
                    + " was accessed\n");
            System.out.print("The amount of  $" + transferAmount +"was "
                    + "transfered from their credit account to their checking account"
                    + "\n");
            aCustomer[loc] = new Customer(aCustomer[loc].firstName,
                    aCustomer[loc].lastName,
                    aCustomer[loc].dateOfBirth,
                    aCustomer[loc].GetId(),
                    aCustomer[loc].address,
                    aCustomer[loc].phoneNumber,
                    aCustomer[loc].GetCheckingAccountNumber(),
                    aCustomer[loc].GetSavingsAccountNumber(),
                    aCustomer[loc].GetCreditAccountNumber(),
                    newBalance1 + "",
                    aCustomer[loc].GetSavingsBalance(),
                    newBalance2 + "");

            
            LogChecking log;
            log = new LogChecking();
            log.LogTransfer(fw, aCustomer, loc, transferAmount, accDest);
            Save(aCustomer,numOfCustomers);
            
            

        } else {
            System.out.println("\nMember not found\n");
        }    
    
    }
    
public static void TransferToSavings(Customer [] aCustomer,
                                            int numOfCustomers, int loc,
                                            String accDestNum,
                                            double transferAmount, 
                                            String accSourceNum,
                                            FileWriter fw, int accDest)
                                            throws IOException{
        
        double newBalance1 = 0, newBalance2 = 0;
        int loc2 = 0;

        while(loc2 < numOfCustomers && !accDestNum.equals(aCustomer[loc2].GetSavingsAccountNumber())){
                        loc2++;
        }
        
        System.out.println(aCustomer[loc2].GetCheckingAccountNumber());
        System.out.println(accSourceNum);
        
        if(Double.parseDouble(aCustomer[loc2].GetCheckingBalance()) == 0) {
            System.out.print("You don't have any money in your checking account");
            
        }else if (accSourceNum.equals(
                            aCustomer[loc2].GetCheckingAccountNumber())) {

            newBalance1 = Double.parseDouble(aCustomer[loc2].GetCheckingBalance()) 
                                            - transferAmount;
            
            newBalance2 = Double.parseDouble(aCustomer[loc2].GetSavingsBalance()) 
                                            + transferAmount;
            
            System.out.printf("\nThe account of " 
                              + aCustomer[loc2].GetFirstName()
                    + " " + aCustomer[loc2].GetLastName()
                    + " was accessed\n");
            System.out.print("The amount of  $" + transferAmount +" was "
                    + "transfered from their checking account to their savings account"
                    + "\n");
            aCustomer[loc2] = new Customer(aCustomer[loc2].firstName,
                    aCustomer[loc2].lastName,
                    aCustomer[loc2].dateOfBirth,
                    aCustomer[loc2].GetId(),
                    aCustomer[loc2].address,
                    aCustomer[loc2].phoneNumber,
                    aCustomer[loc2].GetCheckingAccountNumber(),
                    aCustomer[loc2].GetSavingsAccountNumber(),
                    aCustomer[loc2].GetCreditAccountNumber(),
                    newBalance1 + "",
                    newBalance2 + "",
                    aCustomer[loc2].GetCreditBalance());

            
            LogChecking log;
            log = new LogChecking();
            log.LogTransfer(fw, aCustomer, loc2, transferAmount,accDest);
            Save(aCustomer,numOfCustomers);
            
            

        } else {
            System.out.println("\nMember not found\n");
        }    
    
    }

    @Override
    public void PaySomeone(Customer [] aCustomer, int numOfCustomers,
                                  FileWriter fw) throws IOException{
        /*Description: In this method one person is able to pay another person 
                       with the same bank. First the payer's account number is 
                       asked. Then the person getting paid account number is 
                       asked. After  both the payer's account number and payee's
                       account number is returned to this method where both are 
                       used along with loc1(positon of payer's account 
                       information and loc2(position of payee's account 
                       information). 
         */
        
        String payerAccNum, payeeAccNum; 
        int loc1, loc2, payeeAccType;
        double payerBalance, payeeBalance;

        payerAccNum = GetPayerAccountNumber();
        payeeAccType = GetPayeeAccountType();
        payeeAccNum = GetPayeeAccountNumber();
        loc1 = payerRetrieval(payerAccNum,aCustomer,numOfCustomers);
        
        switch(payeeAccType){

            case 1:
                loc2 = payeeRetrievalChecking(payeeAccNum, aCustomer, numOfCustomers);
                AmountPaidChecking(payerAccNum, payeeAccNum, loc1, loc2, numOfCustomers,
                        aCustomer, fw);
                break;
            case 2:
                loc2 = payeeRetrievalCredit(payeeAccNum,aCustomer,numOfCustomers);
                AmountPaidCredit(payerAccNum,payeeAccNum, loc1, loc2, numOfCustomers,
                           aCustomer,fw, payeeAccType); 
                break;
                
            case 3:
                loc2 = payeeRetrievalSavings(payeeAccNum,aCustomer,numOfCustomers);
                AmountPaidSavings(payerAccNum,payeeAccNum, loc1, loc2, numOfCustomers,
                           aCustomer,fw, payeeAccType); 
                break;
        }

    
    }
    
    
    public static String GetPayerAccountNumber(){
    
        //Description: Get payer's account number from the user

        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the account # of the payer?\n");       
    
        String payerAccNum = kbd.nextLine();
        
        return payerAccNum;        
        
    }
    
    public static int GetPayeeAccountType(){
    
        int payeeAccType;
        
        Scanner kbd = new Scanner(System.in);
    
        System.out.println("What is the account type of the person recieving the money?\n"
                         + "TYPE 1 - Checking\n"
                         + "TYPE 2 - Credit\n"
                         + "TYPE 3 - Savings\n");
        
        payeeAccType = kbd.nextInt();
        
        return payeeAccType;
    }
    
    public static String GetPayeeAccountNumber(){
        
        //Description: Get payee's account number from the user
    
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("What is the account # of the person getting paid?\n");
        
        String payeeAccNum = kbd.nextLine();
                
        return payeeAccNum;        
    
    }

    public static int payerRetrieval(String payerAccNum,Customer [] aCustomer,
                                     int numOfCustomers){

        //Description: Retrieves payer's account information.
        int loc1;
        loc1 = 0;

        while (loc1 < numOfCustomers
                && !payerAccNum.equals(aCustomer[loc1].GetCheckingAccountNumber())) {

            loc1++;
        }

        return loc1;

    }
   
    public static int payeeRetrievalChecking(String payeeAccNum,Customer [] aCustomer,
                                     int numOfCustomers){
        int loc2;
        loc2 = 0;

        while (loc2 < numOfCustomers
                && !payeeAccNum.equals(aCustomer[loc2].GetCheckingAccountNumber())) {

            loc2++;
        }

        return loc2;    
    }    
    
    public static int payeeRetrievalCredit(String payeeAccNum,Customer [] aCustomer,
                                     int numOfCustomers){
        int loc2;
        loc2 = 0;

        while (loc2 < numOfCustomers
                && !payeeAccNum.equals(aCustomer[loc2].GetCreditAccountNumber())) {

            loc2++;
        }

        return loc2;    
    }
    
    public static int payeeRetrievalSavings(String payeeAccNum,Customer [] aCustomer,
                                     int numOfCustomers){
        int loc2;
        loc2 = 0;

        while (loc2 < numOfCustomers
                && !payeeAccNum.equals(aCustomer[loc2].GetSavingsAccountNumber())) {

            loc2++;
        }

        return loc2;    
    }

    public static void AmountPaidChecking(String payerAccNum, String payeeAccNum, 
                                          int loc1, int loc2, int numOfCustomers,
                                          Customer [] aCustomer,FileWriter fw)
                                          throws IOException{
    
        /*Description: Takes payee's and payer's account information and uses it
                       to transfer funds from the payer's account into the 
                       payer's account.
        */
        
        double amountPaid;
        double newBalancePayer;
        
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the amount being paid?");
        
        amountPaid = kbd.nextDouble();

        if (Double.parseDouble(aCustomer[loc1].GetCheckingBalance()) > 0){
            if (payerAccNum.equals(aCustomer[loc1].GetCheckingAccountNumber())){
                newBalancePayer = Double.parseDouble(aCustomer[loc1].GetCheckingBalance()) - amountPaid;
                aCustomer[loc1] = new Customer(aCustomer[loc1].firstName,
                        aCustomer[loc1].lastName,
                        aCustomer[loc1].dateOfBirth,
                        aCustomer[loc1].GetId(),
                        aCustomer[loc1].address,
                        aCustomer[loc1].phoneNumber,
                        aCustomer[loc1].GetCheckingAccountNumber(),
                        aCustomer[loc1].GetSavingsAccountNumber(),
                        aCustomer[loc1].GetCreditAccountNumber(),
                        newBalancePayer + "",
                        aCustomer[loc1].GetSavingsBalance(),
                        aCustomer[loc1].GetCreditBalance());
        
                if (payeeAccNum.equals(aCustomer[loc2].GetCheckingAccountNumber())){
                    double newBalancePayee = Double.parseDouble(aCustomer[loc2].GetCreditBalance()) + amountPaid;
                    aCustomer[loc2] = new Customer(aCustomer[loc1].firstName,
                            aCustomer[loc2].lastName,
                            aCustomer[loc2].dateOfBirth,
                            aCustomer[loc2].GetId(),
                            aCustomer[loc2].address,
                            aCustomer[loc2].phoneNumber,
                            aCustomer[loc2].GetCheckingAccountNumber(),
                            aCustomer[loc2].GetSavingsAccountNumber(),
                            aCustomer[loc2].GetCreditAccountNumber(),
                            newBalancePayee + "",
                            aCustomer[loc2].GetSavingsBalance(),
                            aCustomer[loc2].GetCreditBalance());
                    
                    LogChecking log;
                    log = new LogChecking();
                    log.LogPaymentToSomeone(loc2, loc1, loc2, numOfCustomers, aCustomer, fw, amountPaid);
                    Save(aCustomer,numOfCustomers);

                }
                System.out.println("The Transaction was completed");
            }
            else {
                System.out.println("Account couldn't be found");
            }
        }
        else {
        
            System.out.println("Your account is in the negative");
        }    
    
    }

    public static void AmountPaidCredit(String payerAccNum, String payeeAccNum, 
                                          int loc1, int loc2, int numOfCustomers,
                                          Customer [] aCustomer,FileWriter fw,
                                          int payeeAccType)
                                          throws IOException{
    
        /*Description: Takes payee's and payer's account information and uses it
                       to transfer funds from the payer's account into the 
                       payer's account.
        */
        
        double amountPaid;
        double newBalancePayer;
        
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the amount being paid?");
        
        amountPaid = kbd.nextDouble();

        if (Double.parseDouble(aCustomer[loc1].GetCheckingBalance()) > 0){
            if (payerAccNum.equals(aCustomer[loc1].GetCheckingAccountNumber())){
                newBalancePayer = Double.parseDouble(aCustomer[loc1].GetCheckingBalance()) - amountPaid;
                aCustomer[loc1] = new Customer(aCustomer[loc1].firstName,
                        aCustomer[loc1].lastName,
                        aCustomer[loc1].dateOfBirth,
                        aCustomer[loc1].GetId(),
                        aCustomer[loc1].address,
                        aCustomer[loc1].phoneNumber,
                        aCustomer[loc1].GetCheckingAccountNumber(),
                        aCustomer[loc1].GetSavingsAccountNumber(),
                        aCustomer[loc1].GetCreditAccountNumber(),
                        newBalancePayer + "",
                        aCustomer[loc1].GetSavingsBalance(),
                        aCustomer[loc1].GetCreditBalance());
        
                if (payeeAccNum.equals(aCustomer[loc2].GetCreditAccountNumber())){
                    double newBalancePayee = Double.parseDouble(aCustomer[loc2].GetCreditBalance()) + amountPaid;
                    aCustomer[loc2] = new Customer(aCustomer[loc1].firstName,
                            aCustomer[loc2].lastName,
                            aCustomer[loc2].dateOfBirth,
                            aCustomer[loc2].GetId(),
                            aCustomer[loc2].address,
                            aCustomer[loc2].phoneNumber,
                            aCustomer[loc2].GetCheckingAccountNumber(),
                            aCustomer[loc2].GetSavingsAccountNumber(),
                            aCustomer[loc2].GetCreditAccountNumber(),
                            aCustomer[loc2].GetCheckingBalance(),
                            aCustomer[loc2].GetSavingsBalance(),
                            newBalancePayee + "");
                    
                    LogChecking log;
                    log = new LogChecking();
                    log.LogPaymentToSomeone(payeeAccType, loc1, loc2, numOfCustomers, aCustomer, fw, amountPaid);
                    Save(aCustomer,numOfCustomers);

                }
                System.out.println("The Transaction was completed");
            }
            else {
                System.out.println("Account couldn't be found");
            }
        }
        else {
        
            System.out.println("Your account is in the negative");
        }    
    
    }
    
    public static void AmountPaidSavings(String payerAccNum, String payeeAccNum, 
                                          int loc1, int loc2, int numOfCustomers,
                                          Customer [] aCustomer,FileWriter fw,
                                          int payeeAccType)
                                          throws IOException{
    
        /*Description: Takes payee's and payer's account information and uses it
                       to transfer funds from the payer's account into the 
                       payer's account.
        */
        
        double amountPaid;
        double newBalancePayer;
        
        Scanner kbd = new Scanner(System.in);
        System.out.println("What is the amount being paid?");
        
        amountPaid = kbd.nextDouble();

        if (Double.parseDouble(aCustomer[loc1].GetCheckingBalance()) > 0){

            if (payerAccNum.equals(aCustomer[loc1].GetCheckingAccountNumber())){
                newBalancePayer = Double.parseDouble(aCustomer[loc1].GetCheckingBalance()) - amountPaid;
                aCustomer[loc1] = new Customer(aCustomer[loc1].firstName,
                        aCustomer[loc1].lastName,
                        aCustomer[loc1].dateOfBirth,
                        aCustomer[loc1].GetId(),
                        aCustomer[loc1].address,
                        aCustomer[loc1].phoneNumber,
                        aCustomer[loc1].GetCheckingAccountNumber(),
                        aCustomer[loc1].GetSavingsAccountNumber(),
                        aCustomer[loc1].GetCreditAccountNumber(),
                        newBalancePayer + "",
                        aCustomer[loc1].GetSavingsBalance(),
                        aCustomer[loc1].GetSavingsBalance());
        
                if (payeeAccNum.equals(aCustomer[loc2].GetSavingsAccountNumber())){
                    double newBalancePayee = Double.parseDouble(aCustomer[loc2].GetSavingsBalance()) + amountPaid;
                    aCustomer[loc2] = new Customer(aCustomer[loc1].firstName,
                            aCustomer[loc2].lastName,
                            aCustomer[loc2].dateOfBirth,
                            aCustomer[loc2].GetId(),
                            aCustomer[loc2].address,
                            aCustomer[loc2].phoneNumber,
                            aCustomer[loc2].GetCheckingAccountNumber(),
                            aCustomer[loc2].GetSavingsAccountNumber(),
                            aCustomer[loc2].GetCreditAccountNumber(),
                            aCustomer[loc2].GetCheckingBalance(),
                            newBalancePayee + "",
                            aCustomer[loc2].GetCreditBalance());
                    
                    LogChecking log;
                    log = new LogChecking();
                    log.LogPaymentToSomeone(payeeAccType, loc1, loc2, numOfCustomers, aCustomer, fw, amountPaid);
                    Save(aCustomer,numOfCustomers);                    
                    
                }
                System.out.println("The Transaction was completed");
            }
            else {
                System.out.println("Account couldn't be found");
            }
        }
        else {
            System.out.println("Your account is in the negative ");
        }    
    
    }    
    public static void Save(Customer[] aCustomer, int size)
            throws IOException {

        /*Description: Write the most current information from the CustomerInfo
                       into the Bank user file.*/
        FileWriter fw;

        File bankFile = new File("CS 3331 - Bank Users 2.csv");

        fw = new FileWriter(bankFile, false);

        fw.write("ï»¿First Name,Last Name,Date of Birth,IdentificatioNumber"
                + ",Address,Phone Number,Checking Account Number,Savings "
                + "Account Number,Credit Account Number,Checking Starting "
                + "Balance,Savings Starting Balance,Credit Starting Balance\n");

        for (int cnt = 0; cnt < aCustomer.length; cnt++) {
            String data = aCustomer[cnt].GetFirstName() + ","
                    + aCustomer[cnt].GetLastName() + ","
                    + aCustomer[cnt].dateOfBirth + ","
                    + aCustomer[cnt].GetId() + ","
                    + aCustomer[cnt].address + ","
                    + aCustomer[cnt].phoneNumber + ","
                    + aCustomer[cnt].GetCheckingAccountNumber()
                    + "," + aCustomer[cnt].GetSavingsAccountNumber()
                    + "," + aCustomer[cnt].GetCreditAccountNumber()
                    + "," + aCustomer[cnt].GetCheckingBalance()
                    + "," + aCustomer[cnt].GetSavingsBalance()
                    + "," + aCustomer[cnt].GetCreditBalance();

            fw.write(data + "\n");

        }

        fw.close();

        System.out.println("Information has been updated");

    }    

}