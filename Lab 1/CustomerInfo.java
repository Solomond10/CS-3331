package checking;

import java.util.Scanner;
import java.io.*;

public class CustomerInfo {

    private String firstName, lastName, checkingAccount,
            savingsAccount, interestRate;

    private int accountNumber;

    private double balance;

    public void Load(Scanner bankSc)throws IOException {

        firstName = bankSc.next();
        lastName = bankSc.next();
        accountNumber = bankSc.nextInt();
        checkingAccount = bankSc.next();
        savingsAccount = bankSc.next();
        balance = bankSc.nextDouble();
        interestRate = bankSc.next();

    }

    public void Set(String nFirstName, String nLastName, int nAccountNumber,
                    String nCheckingAccount, String nSavingsAccount,
                    double nBalance, String nInterestRate) {

        firstName = nFirstName;
        lastName = nLastName;
        accountNumber = nAccountNumber;
        checkingAccount = nCheckingAccount;
        savingsAccount = nSavingsAccount;
        balance = nBalance;
        interestRate = nInterestRate;

    }

    public String GetFirstName() {

        return firstName;
    }

    public String GetLastName() {

        return lastName;

    }

    public int GetAccountNumber() {

        return accountNumber;

    }

    public String GetCheckingAccount() {

        return checkingAccount;

    }

    public String GetSavingsAccount() {

        return savingsAccount;

    }

    public double GetBalance() {

        return balance;

    }

    public String GetInterstRate() {

        return interestRate;

    }

}
