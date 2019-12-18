package programmingassignment5;

/**
 *
 * @author admin
 */
public abstract class Account {
	
	int accountNum;
	double accountBalance;
	
    /**
     * Constructor
     */
    public Account() { //default constructor
		
	}
	
    /**
     * Constructor with account number and balance
     * @param accountNum
     * @param accountBalance
     */
    public Account(int accountNum, double accountBalance) {
		this.accountNum = accountNum;
		this.accountBalance = accountBalance;
	}
	
    /**
     * Sets customer's account balance
     * @param accountBalance
     */
    public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
    /**
     * Sets customer's account number
     * @param accountNum
     */
    public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	
	//the getters

    /**
     * Gets customer's account number
     * @return
     */
	public int getAccountNum() {
		return this.accountNum;
	}
	
    /**
     * Gets customer's account balance
     * @return
     */
    public double getAccountBalance() {
		return this.accountBalance;
	}
	
	//abstract methods

    /**
     * Deposits amount into customer account
     * @param depositAmount
     */
	public abstract void deposit(double depositAmount);

    /**
     * withdraws amount from customer account
     * @param withdrawAmount
     */
    public abstract void withdraw(double withdrawAmount);

    /**
     * Transfer amount into customer account
     * @param transferAmount
     * @param a
     */
    public abstract void transfer(double transferAmount, Account a);
}
