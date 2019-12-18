package programmingassignment5;

import java.util.*;

/**
 *
 * @author admin
 */
public class BankStatement implements Log {
	
	List<String> bankStatement;// = new ArrayList(); 
        
    /**
     * Constructor 
     */
    public BankStatement() {  //Solomon Davis
		
	}
	
    /**
     *Constructor with list
     * @param bankStatement
     */
    public BankStatement(List<String> bankStatement) {//Solomon Davis
		this.bankStatement = bankStatement;
	}
	
    /**
     *
     * @return
     */
    public List<String> getList() {//Solomon Davis
		return this.bankStatement;
	}        

    /**
     * Adds to the bank statement list when a deposit is made using the checking account 
     * @param amount
     * @param p
     */
    @Override
	public void writeDepositChecking(double amount, Person p) {//Solomon Davis
		bankStatement.add(p.getFirstName() + " " + p.getLastName() + " deposited " + "$" + 
	amount + " into Checking-" + p.getChecking().getAccountNum());
                bankStatement.add("DC Checking-" + p.getChecking().getAccountNum()+" "+amount);
                
		
	}

    /**
     * Adds to the bank statement list when a transfer is made from checking to savings
     * @param p
     * @param amount
     */
    @Override
	public void writeTransferCtoS(Person p, double amount) { //checking to savings //Solomon Davis
		bankStatement.add(p.getFirstName() + " " + p.getLastName() + " transfered " + "$" + 
				amount + " from Checking-" + p.getChecking().getAccountNum() +" to Savings-" + 
							p.getSavings().getAccountNum());
                bankStatement.add("TCS Savings-" + p.getSavings().getAccountNum()+" "+amount);
					
		
	}

    /**
     * Adds to the bank statement list when a deposit is made using the savings account 
     * @param amount
     * @param p
     */
    @Override
	public void writeDepositSavings(double amount, Person p) {//Solomon Davis
		bankStatement.add(p.getFirstName() + " " + p.getLastName() + " deposited " + "$" + 
	amount + " into Savings-" + p.getSavings().getAccountNum());
                bankStatement.add("DS Savings-" + p.getSavings().getAccountNum()+" "+amount);
                
		
	}

    /**
     *Adds to the bank statement list when a withdrawal is made using the checking account
     * @param amount
     * @param p
     */
    @Override
	public void writeWithdrawChecking(double amount, Person p) {//Solomon Davis
		bankStatement.add(p.getFirstName() + " " + p.getLastName() + " withdrew " + "$" + 
	amount + " from Checking-" + p.getChecking().getAccountNum());
                bankStatement.add("WC Checking-" + p.getChecking().getAccountNum()+" "+amount);
		
	}

    /**
     *Adds to the bank statement list when a withdrawal is made using the savings account
     * @param amount
     * @param p
     */
    @Override
	public void writeWithdrawSavings(double amount, Person p) {//Solomon Davis
		bankStatement.add(p.getFirstName() + " " + p.getLastName() + " withdrew " + "$" + 
	amount + " from Savings-" + p.getSavings().getAccountNum());
                bankStatement.add("WS Savings-" + p.getSavings().getAccountNum()+" "+amount);
		
	}

    /**
     *Adds to the bank statement list when a payment is made from one account to another
     * @param p1
     * @param p2
     * @param amount
     */
    @Override
	public void writePay(Person p1, Person p2, double amount) {//Solomon Davis
		bankStatement.add(p1.getFirstName() + " " + p1.getLastName() + " payed " + "$" + 
				amount + " from Checking-" + p1.getChecking().getAccountNum() +" to " + 
							p2.getFirstName() + " " + p2.getLastName());
                
                bankStatement.add("PF Checking-" + p1.getChecking().getAccountNum()+" "+amount);
                
                bankStatement.add(p2.getFirstName() + " " + p2.getLastName() + " was payed " + "$" + 
				amount + " from Checking-" + p1.getChecking().getAccountNum() +" by " + 
							p1.getFirstName() + " " + p1.getLastName());
                bankStatement.add("BP Checking-" + p2.getChecking().getAccountNum()+" "+amount);
		
	}

    /**
     *Adds to the bank statement list when a transfer is made from checking to credit
     * @param p
     * @param amount
     */
    @Override
	public void writeTransferCtoCC(Person p, double amount) { //checking to credit //Solomon Davis
		bankStatement.add(p.getFirstName() + " " + p.getLastName() + " transfered " + "$" + 
				amount + " from Checking-" + p.getChecking().getAccountNum() +" to Credit-" + 
							p.getCreditCard().getAccountNum());
                bankStatement.add("TCCR Credit Card-" + p.getCreditCard().getAccountNum()+" "+amount);
		
	}

    /**
     *Adds to the bank statement list when a transfer is made from savings to checking
     * @param p
     * @param amount
     */
    @Override
	public void writeTransferStoC(Person p, double amount) { //savings to checking //Solomon Davis
		bankStatement.add(p.getFirstName() + " " + p.getLastName() + " transfered " + "$" + 
				amount + " from Savings-" + p.getSavings().getAccountNum() +" to Checking-" + 
							p.getChecking().getAccountNum());
                bankStatement.add("TSC Checking-" + p.getChecking().getAccountNum()+" "+amount);
		
	}

    /**
     *Adds to the bank statement list when a transfer is made from savings to credit
     * @param p
     * @param amount
     */
    @Override
	public void writeTransferStoCC(Person p, double amount) { //savings to credit //Solomon Davis
		bankStatement.add(p.getFirstName() + " " + p.getLastName() + " transfered " + "$" + 
				amount + " from Savings-" + p.getSavings().getAccountNum() +" to Credit-" + 
							p.getCreditCard().getAccountNum());
                bankStatement.add("TSCR Credit Card-" + p.getCreditCard().getAccountNum()+" "+amount);
		
	}
	
	
}
