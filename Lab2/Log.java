package bank;

import java.io.*;

    public interface Log{
    
        public void LogInquiry(String accNum, Customer[] aCustomer, int numOfCustomers,
                           FileWriter fw, int loc)
                           throws IOException;
        
        public void LogWithdrawal(FileWriter fw, Customer [] aCustomer, int loc, 
                            double transferAmount, int accDest, 
                            double newBalance) throws IOException;
        
        public void LogDeposit(Customer [] aCustomer, int numOfCustomers, 
                              int loc, double depositAmount, FileWriter fw,
                              double newBalance)throws IOException;
        
        public void LogTransfer(FileWriter fw, Customer [] aCustomer, int loc2, 
                            double transferAmount, int accDest) throws IOException;
        
        public void LogPaymentToSomeone(int payeeAccType, int loc1, int loc2,
                                    int numOfCustomers, Customer[] aCustomer, 
                                    FileWriter fw, double amountPaid)
                                    throws IOException;
    }


class LogCredit implements Log {

    @Override
    public void LogInquiry(String accNum, Customer[] aCustomer, int numOfCustomers,
                           FileWriter fw, int loc)
                           throws IOException {
        
            fw.write(aCustomer[loc].GetFirstName() + " "
                    + aCustomer[loc].GetLastName()
                    + " made a balance inquiry " 
                    + " on Credit-" + aCustomer[loc].GetCreditAccountNumber()
                    + ". " + aCustomer[loc].GetFirstName() +" "
                    + aCustomer[loc].GetLastName() + "'s balance for Credit-"
                    + aCustomer[loc].GetCreditAccountNumber() + ":$"
                    + aCustomer[loc].GetCreditBalance()+"\n");        
    }

    @Override
    public void LogWithdrawal(FileWriter fw, Customer [] aCustomer, int loc, 
                            double withdrawalAmount, int accDest, 
                            double newBalance)
                            throws IOException{
        
        fw.write(aCustomer[loc].GetFirstName() + " "
                + aCustomer[loc].GetLastName()
                + " withdrew $" + withdrawalAmount
                + " in Credit-" + aCustomer[loc].GetCreditBalance()
                + ". " + aCustomer[loc].GetFirstName() + " "
                + aCustomer[loc].GetLastName() + "'s new balance "
                + "for Credit-"
                + aCustomer[loc].GetCreditBalance() + ":$"
                + newBalance + "\n");
        System.out.print("New Balance $" + newBalance + "\n");
        System.out.print("Withrawal Complete\n");    
    }

    @Override
    public void LogDeposit(Customer [] aCustomer, int numOfCustomers, 
                              int loc, double depositAmount, FileWriter fw,
                              double newBalance) 
                              throws IOException {
        
        fw.write(aCustomer[loc].GetFirstName() + " "
                + aCustomer[loc].GetLastName()
                + " deposited" + depositAmount
                + " in Savings-" + aCustomer[loc].GetCreditAccountNumber()
                + ". " + aCustomer[loc].GetFirstName()
                + aCustomer[loc].GetLastName() + "'s new balance for Credit-"
                + aCustomer[loc].GetCreditAccountNumber() + ":$"
                + newBalance + "\n");
    }

    @Override
    public void LogTransfer(FileWriter fw, Customer [] aCustomer, int loc2, 
                            double transferAmount, int accDest)
                            throws IOException{
        
        switch(accDest){
        
            case 1:
                fw.write(aCustomer[loc2].GetFirstName()
                        + " "
                        + aCustomer[loc2].GetLastName()
                        + " transferred $" + transferAmount
                        + " from their credit account to their checking account\n");
                System.out.println("Log Complete\n");
                break;
                
            case 3:
                fw.write(aCustomer[loc2].GetFirstName()
                        + " "
                        + aCustomer[loc2].GetLastName()
                        + " transferred $" + transferAmount
                        + " from their credit account to their savings account\n");
                System.out.println("Log Complete\n");
                break;
        }
    }

    @Override
    public void LogPaymentToSomeone(int payeeAccType, int loc1, int loc2,
                                    int numOfCustomers, Customer[] aCustomer, 
                                    FileWriter fw, double amountPaid)
                                    throws IOException{
        switch(payeeAccType){
            case 1:
                fw.write(aCustomer[loc1].GetFirstName() + " "
                        + aCustomer[loc1].GetLastName()
                        + " paid " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + " $" + amountPaid + " from "
                        + "Credit-" + aCustomer[loc1].GetCreditAccountNumber()
                        + ". " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + "'s new balance for Checking-"
                        + aCustomer[loc2].GetCheckingAccountNumber() + ":$"
                        + aCustomer[loc2].GetCheckingBalance() + "\n");
                break;
                
            case 3:
                fw.write(aCustomer[loc1].GetFirstName() + " "
                        + aCustomer[loc1].GetLastName()
                        + " paid " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + " $" + amountPaid + " from "
                        + "Credit-" + aCustomer[loc1].GetCreditAccountNumber()
                        + ". " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + "'s new balance for Savings-"
                        + aCustomer[loc2].GetSavingsAccountNumber() + ":$"
                        + aCustomer[loc2].GetSavingsBalance() + "\n");
                break;
        
        }

    }
}

class LogSavings implements Log {

    @Override
    public void LogInquiry(String accNum, Customer[] aCustomer, int numOfCustomers,
                           FileWriter fw, int loc)
                           throws IOException {

           fw.write(aCustomer[loc].GetFirstName() + " "
                    + aCustomer[loc].GetLastName()
                    + " made a balance inquiry " 
                    + " on Savings-" + aCustomer[loc].GetSavingsAccountNumber()
                    + ". " + aCustomer[loc].GetFirstName() +" "
                    + aCustomer[loc].GetLastName() + "'s balance for Savings-"
                    + aCustomer[loc].GetSavingsAccountNumber() + ":$"
                    + aCustomer[loc].GetSavingsBalance()+"\n");
        
    }

    @Override
    public void LogWithdrawal(FileWriter fw, Customer [] aCustomer, int loc, 
                            double withdrawalAmount, int accDest, 
                            double newBalance)
                            throws IOException{
        
            fw.write(aCustomer[loc].GetFirstName() + " "
                    + aCustomer[loc].GetLastName()
                    + " withdrew $" + withdrawalAmount
                    + " in Credit-" + aCustomer[loc].GetCreditBalance()
                    + ". " + aCustomer[loc].GetFirstName() +" "
                    + aCustomer[loc].GetLastName() + "'s new balance for Credit-"
                    + aCustomer[loc].GetCreditBalance() + ":$"
                    + newBalance+"\n");
            System.out.print("New Balance $" + newBalance + "\n");
            System.out.print("Withrawal Complete\n");
                    
    }

    @Override
    public void LogDeposit(Customer [] aCustomer, int numOfCustomers, 
                              int loc, double depositAmount, FileWriter fw,
                              double newBalance) 
                              throws IOException {

        fw.write(aCustomer[loc].GetFirstName() + " "
                + aCustomer[loc].GetLastName()
                + " deposited" + depositAmount
                + " in Savings-" + aCustomer[loc].GetSavingsAccountNumber()
                + ". " + aCustomer[loc].GetFirstName()
                + aCustomer[loc].GetLastName() + "'s new balance for Savings-"
                + aCustomer[loc].GetSavingsAccountNumber() + ":$"
                + newBalance + "\n");
    }

    @Override
    public void LogTransfer(FileWriter fw, Customer [] aCustomer, int loc2, 
                            double transferAmount, int accDest)
                            throws IOException{
        
        switch(accDest){
        
            case 1:
                fw.write(aCustomer[loc2].GetFirstName()
                        + " "
                        + aCustomer[loc2].GetLastName()
                        + " transferred $" + transferAmount
                        + " from their savings account to their checking account\n");
                System.out.println("Log Complete\n");
                break;
                
            case 2:
                fw.write(aCustomer[loc2].GetFirstName()
                        + " "
                        + aCustomer[loc2].GetLastName()
                        + " transferred $" + transferAmount
                        + " from their savings account to their credit account\n");
                System.out.println("Log Complete\n");
                break;
        }                
    }

    @Override
    public void LogPaymentToSomeone(int payeeAccType, int loc1, int loc2,
                                    int numOfCustomers, Customer[] aCustomer, 
                                    FileWriter fw, double amountPaid)
                                    throws IOException {
  
        switch(payeeAccType){
            case 1:
                fw.write(aCustomer[loc1].GetFirstName() + " "
                        + aCustomer[loc1].GetLastName()
                        + " paid " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + " $" + amountPaid + " from "
                        + "Savings-" + aCustomer[loc1].GetSavingsAccountNumber()
                        + ". " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + "'s new balance for Checking-"
                        + aCustomer[loc2].GetCheckingAccountNumber() + ":$"
                        + aCustomer[loc2].GetCheckingBalance() + "\n");
                break;
                
            case 2:
                fw.write(aCustomer[loc1].GetFirstName() + " "
                        + aCustomer[loc1].GetLastName()
                        + " paid " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + " $" + amountPaid + " from "
                        + "Savings-" + aCustomer[loc1].GetSavingsAccountNumber()
                        + ". " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + "'s new balance for Credit-"
                        + aCustomer[loc2].GetCreditAccountNumber() + ":$"
                        + aCustomer[loc2].GetCreditBalance() + "\n");
                break;                
                
            case 3:
                fw.write(aCustomer[loc1].GetFirstName() + " "
                        + aCustomer[loc1].GetLastName()
                        + " paid " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + " $" + amountPaid + " from "
                        + "Savings-" + aCustomer[loc1].GetSavingsAccountNumber()
                        + ". " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + "'s new balance for Savings-"
                        + aCustomer[loc2].GetSavingsAccountNumber() + ":$"
                        + aCustomer[loc2].GetSavingsBalance() + "\n");
                break;
        
        }        
   
    }

}

class LogChecking implements Log {

    @Override
    public void LogInquiry(String accNum, Customer[] aCustomer, int numOfCustomers,
                           FileWriter fw, int loc)
                           throws IOException {
        
         fw.write(aCustomer[loc].GetFirstName() + " "
                    + aCustomer[loc].GetLastName()
                    + " made a balance inquiry " 
                    + " on Checking-" 
                    + aCustomer[loc].GetCheckingAccountNumber()
                    + ". " + aCustomer[loc].GetFirstName() +" "
                    + aCustomer[loc].GetLastName() + "'s balance for Checking-"
                    + aCustomer[loc].GetCheckingAccountNumber() + ":$"
                    + aCustomer[loc].GetCheckingBalance()+"\n");  
        
    }

    @Override
    public void LogWithdrawal(FileWriter fw, Customer [] aCustomer, int loc, 
                            double withdrawalAmount, int accDest, 
                            double newBalance)
                            throws IOException{
        
            fw.write(aCustomer[loc].GetFirstName() + " "
                    + aCustomer[loc].GetLastName()
                    + " withdrew $" + withdrawalAmount
                    + " in Checking-" 
                    + aCustomer[loc].GetCheckingAccountNumber()
                    + ". " + aCustomer[loc].GetFirstName() +" "
                    + aCustomer[loc].GetLastName() 
                    + "'s new balance for Checking-"
                    + aCustomer[loc].GetCheckingAccountNumber() + ":$"
                    + newBalance+"\n");
            System.out.print("New Balance $" + newBalance + "\n");
            System.out.print("Withrawal Complete\n");
    }

    @Override
    public void LogDeposit(Customer [] aCustomer, int numOfCustomers, 
                              int loc, double depositAmount, FileWriter fw,
                              double newBalance) 
                              throws IOException {
        
        fw.write(aCustomer[loc].GetFirstName() + " "
                + aCustomer[loc].GetLastName()
                + " deposited $" + depositAmount
                + " in Checking-"
                + aCustomer[loc].GetCheckingAccountNumber()
                + ". " + aCustomer[loc].GetFirstName() + " "
                + aCustomer[loc].GetLastName()
                + "'s new balance for Checking-"
                + aCustomer[loc].GetCheckingAccountNumber() + ":$"
                + newBalance + "\n");
    }

    @Override
    public void LogTransfer(FileWriter fw, Customer [] aCustomer, int loc2, 
                            double transferAmount, int accDest)
                            throws IOException{
        switch(accDest){
        
            case 2:
                fw.write(aCustomer[loc2].GetFirstName()
                        + " "
                        + aCustomer[loc2].GetLastName()
                        + " transferred $" + transferAmount
                        + " from their checking account to their savings account\n");
                System.out.println("Log Complete\n");
                break;
                
            case 3:
                fw.write(aCustomer[loc2].GetFirstName()
                        + " "
                        + aCustomer[loc2].GetLastName()
                        + " transferred $" + transferAmount
                        + " from their checking account to their credit account\n");
                System.out.println("Log Complete\n");
                break;
        }                
    }

    @Override
    public void LogPaymentToSomeone(int payeeAccType, int loc1, int loc2,
                                    int numOfCustomers, Customer[] aCustomer, 
                                    FileWriter fw, double amountPaid)
                                    throws IOException {
    
        switch(payeeAccType){
            case 1:
                fw.write(aCustomer[loc1].GetFirstName() + " "
                        + aCustomer[loc1].GetLastName()
                        + " paid " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + " $" + amountPaid + " from "
                        + "Checking-" + aCustomer[loc1].GetCheckingAccountNumber()
                        + ". " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + "'s new balance for Checking-"
                        + aCustomer[loc2].GetCheckingAccountNumber() + ":$"
                        + aCustomer[loc2].GetCheckingBalance() + "\n");
                System.out.println("1");
                break;
                
            case 2:
                fw.write(aCustomer[loc1].GetFirstName() + " "
                        + aCustomer[loc1].GetLastName()
                        + " paid " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + " $" + amountPaid + " from "
                        + "Checking-" + aCustomer[loc1].GetCheckingAccountNumber()
                        + ". " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + "'s new balance for Credit-"
                        + aCustomer[loc2].GetCreditAccountNumber() + ":$"
                        + aCustomer[loc2].GetCreditBalance() + "\n");
                System.out.println("2");
                break;                
                
            case 3:
                fw.write(aCustomer[loc1].GetFirstName() + " "
                        + aCustomer[loc1].GetLastName()
                        + " paid " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + " $" + amountPaid + " from "
                        + "Checking-" + aCustomer[loc1].GetCheckingAccountNumber()
                        + ". " + aCustomer[loc2].GetFirstName() + " "
                        + aCustomer[loc2].GetLastName() + "'s new balance for Savings-"
                        + aCustomer[loc2].GetSavingsAccountNumber() + ":$"
                        + aCustomer[loc2].GetSavingsBalance() + "\n");
                System.out.println("3");
                break;
        
        } 
        
    }
}
    