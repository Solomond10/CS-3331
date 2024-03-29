package programmingassignment5;


public class Checking extends Account{
	
	public Checking() { //default constructor
		
	}
	
	public Checking(int accountNum, double accountBalance) {
		
		super(accountNum, accountBalance);
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
			this.accountBalance += 0; //does nothing to the balance
		}
		
		else {
			this.accountBalance += depositAmount; //adds to current balance
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;
		}
		
	}

        /**
        * Withdraws money for customer who is logged in from their choice of account
        * @param withdrawAmount
        */        
	@Override
	public void withdraw(double withdrawAmount) {
            
            double roundingScale;
         
            roundingScale = Math.pow(10,2);
            withdrawAmount = Math.round(withdrawAmount * roundingScale) / roundingScale;            
            
		if(withdrawAmount > this.accountBalance) {
			this.accountBalance += 0; //does nothing to the balance
		}
		
		else {
			this.accountBalance -= withdrawAmount;//subtracts from current balance
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;                        
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
            transferAmount = Math.round(transferAmount * roundingScale) / roundingScale;      // rounds value to 2 decimal places      
            
		if(transferAmount > this.accountBalance) {
			this.accountBalance += 0; //does nothing to balance
		}
		
		else {
			this.accountBalance -= transferAmount;//subtracts from account
                        this.accountBalance = Math.round(this.accountBalance * roundingScale) / roundingScale;                        
			a.accountBalance += transferAmount;//adds to desired account
		}

		
	}
	
	
}
