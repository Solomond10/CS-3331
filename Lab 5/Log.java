package programmingassignment5;

/**
 *
 * @author admin
 */
public interface Log {
	
    /**
     * Writes to log file when a deposit is made using the checking account 
     * @param amount
     * @param p
     */
    public void writeDepositChecking(double amount, Person p);

    /**
     *Writes to log file when a deposit is made using the savings account 
     * @param amount
     * @param p
     */
    public void writeDepositSavings(double amount, Person p);

    /**
     *Writes to log file when a withdrawal is made using the checking account 
     * @param amount
     * @param p
     */
    public void writeWithdrawChecking(double amount, Person p);

    /**
     *Writes to log file when a withdrawal is made using the savings account
     * @param amount
     * @param p
     */
    public void writeWithdrawSavings(double amount, Person p);

    /**
     *Writes to log file when a transfer is made from checking to savings
     * @param p
     * @param amount
     */
    public void writeTransferCtoS(Person p, double amount);

    /**
     *Writes to log file when a transfer is made from checking to credit
     * @param p
     * @param amount
     */
    public void writeTransferCtoCC(Person p, double amount);

    /**
     *Writes to log file when a transfer is made from savings to checking
     * @param p
     * @param amount
     */
    public void writeTransferStoC(Person p, double amount);

    /**
     *Writes to log file when a transfer is made from savings to credit
     * @param p
     * @param amount
     */
    public void writeTransferStoCC(Person p, double amount);

    /**
     *Writes to log file when a payment is made from one account to another
     * @param p1
     * @param p2
     * @param amount
     */
    public void writePay(Person p1, Person p2, double amount);
}
