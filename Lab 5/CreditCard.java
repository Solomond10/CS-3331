package programmingassignment5;


public class CreditCard extends Account{
	
	double creditMax;
	
        /**
        * Constructor
        */                
	public CreditCard() { //default constructor
		
	}

        /**
        * transfers money from one customer's account to another
        * @param accountNum
        * @param accountBalance
        * @param creditMax
        */                
	public CreditCard(int accountNum, double accountBalance, double creditMax) {
		this.accountNum = accountNum;
		this.accountBalance = accountBalance;
		this.creditMax = creditMax;
	}
        
        
        /**
        * Sets credit max
        * @param creditMax
        */        	
	public void setCreditMax(double creditMax) {
		this.creditMax = creditMax;
	}
        
        /**
        * Returns credit max
        * @return 
        */        	
	public double getCreditMax() {
		return this.creditMax;
	}

        /**
        * Deposits money for customer who is logged in their choice of account
        * @param depositAmount
        */        
	@Override
	public void deposit(double depositAmount) {
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            depositAmount = Math.round(depositAmount * roundingScale) / roundingScale;            
            
		if(depositAmount < 0) {
			this.accountBalance += 0;
		}
		else {
			this.accountBalance += depositAmount;
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;                        
		}
		
	}

        /**
        * Withdraws money for customer who is logged in from their choice of account
        * @param withdrawAmount
        */        
	@Override
	public void withdraw(double withdrawAmount) { //can't withdraw from credit account
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            withdrawAmount = Math.round(withdrawAmount * roundingScale) / roundingScale;            
            
		if(withdrawAmount > this.accountBalance) {
			this.accountBalance -= 0; //does nothing
		}
		else {
			this.accountBalance -= 0;
		}
	}

        /**
        * transfers money from one customer's account to another
        * @param transferAmount
        * @param a
        */            
	@Override
	public void transfer(double transferAmount, Account a) {
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            transferAmount = Math.round(transferAmount * roundingScale) / roundingScale;            
            
		if(transferAmount > this.accountBalance) {
			this.accountBalance += 0;
		}
		else if((transferAmount + this.accountBalance) > creditMax){ //checks if it's under the max
			this.accountBalance += 0; //does nothing
		}
		
		else {
			this.accountBalance -= transferAmount;//subtracts from account
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;                        
			a.accountBalance += transferAmount;//adds to desired account
		}
	}
	
	
}
