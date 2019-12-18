package programmingassignment5;

/**
 * 
 * @author admin
 */
public abstract class Person {
	
	String firstName;
	String lastName;
	String DOB;
	String phoneNum;
	String address;
	String email;

	
	//setters

    /**
     * Sets first name for customer
     * @param firstName
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
    /**
     * Sets last name for customer
     * @param lastName
     */
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
    /**
     * Sets date of birth for customer
     * @param DOB
     */
    public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	
    /**
     * Sets phone number for customer
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
    /**
     * Sets address for customer
     * @param address
     */
    public void setAddress(String address) {
		this.address = address;
	}
	
    /**
     * Sets email for customer
     * @param email
     */
    public void setEmail(String email) {
		this.email = email;
	}
	/*
	public void setAccount(account3 account) {
		this.account = account;
	}
	*/
	
	//getters
        
	
        /**
        *Returns First Name
        * @return
        */        
	public String getFirstName() {
		return this.firstName;
	}
	
        /**
        *Returns Last Name
        * @return
        */
	public String getLastName() {
		return lastName;
	}
	
	
        /**
        *Returns Date Of birth
        * @return
        */        
	public String getDOB() {
		return this.DOB;
	}
	
	
        /**
        *Returns Phone number
        * @return
        */        
	public String getPhoneNum() {
		return this.phoneNum;
	}
	
	
        /**
        *Returns Address
        * @return
        */        
	public String getAddress() {
		return this.address;
	}
	
	
        /**
        *Returns Email
        * @return
        */        
	public String getEmail() {
		return this.email;
	}

	//abstract methods

    /**
     * Sets customer id
     * @param ID
     */
	public abstract void setID(int ID);

    /**
     * Gets customer id
     * @return
     */
    public abstract int getID();

    /**
     * Sets customer checking account info
     * @param account
     */
    public abstract void setCheckingAccount(Account account);

    /**
     * Sets customer savings account info
     * @param account
     */
    public abstract void setSavingsAccount(Account account);

    /**
     * Sets customer credit card account info
     * @param account
     */
    public abstract void setCreditCard(CreditCard account);

    /**
     * Returns customer checking account info
     * @return
     */
    public abstract Account getChecking();

    /**
     * Returns customer savings account info
     * @return
     */
    public abstract Account getSavings();

    /**
     * Returns customer credit card account info
     * @return
     */
    public abstract Account getCreditCard();

    /**
     * Prints customer info
     */
    public abstract void printCustomerInfo();

    /**
     * Sets password for customer
     * @param password
     */
    public abstract void setPassword(String password);

    /**
     * Gets password for customer 
     * @return
     */
    public abstract String getPassword();
}
